package cn.sweetyhut.ezoa.service.impl;

import cn.sweetyhut.ezoa.config.MiniProgramConfig;
import cn.sweetyhut.ezoa.constant.FrontConst;
import cn.sweetyhut.ezoa.constant.KeyPrefixConst;
import cn.sweetyhut.ezoa.constant.WxApiConst;
import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.service.UserLoginService;
import cn.sweetyhut.ezoa.utils.AesCbuUtil;
import cn.sweetyhut.ezoa.utils.ResponseUtil;
import cn.sweetyhut.ezoa.utils.requestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/30 18:11
 */
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {
    private MiniProgramConfig config;
    private StringRedisTemplate template;

    @Autowired
    public UserLoginServiceImpl(MiniProgramConfig config, StringRedisTemplate template) {
        this.config = config;
        this.template = template;
    }

    @Override
    public MiniResponse login(String code, String encryptedData, String iv) {
        Map<String, Object> data = new HashMap<>();

        //code2session API 成功返回openid和session_key , 失败返回errcode errmsg
        String requestParam = "appid=" + config.getAppId() + "&secret=" + config.getSecretKey() + "&js_code=" + code + "&grant_type=" + WxApiConst.GRANT_TYPE;
        String resJsonString = requestUtil.getForObj(config.getWxCode2SessionApi(), requestParam, String.class);
        JsonObject responseBody = new Gson().fromJson(resJsonString, JsonObject.class);

        //判断code2session是否失败
        if (responseBody.has(WxApiConst.CODE_NAME)) {
            Integer errCode = responseBody.get(WxApiConst.CODE_NAME).getAsInt();
            String errMsg = responseBody.get(WxApiConst.MSG_NAME).getAsString();
            return ResponseUtil.error(errCode, errMsg);
        }

        String sessionKey = responseBody.get(WxApiConst.SKEY_NAME).getAsString();
        String openId = responseBody.get(WxApiConst.OPENID_NAME).getAsString();
        data.put(FrontConst.SKEY_NAME, sessionKey);

        if (encryptedData == null || iv == null) {
            data.put(FrontConst.USERINFO_NAME, template.opsForValue().get(KeyPrefixConst.USER_INFO + openId));
            return ResponseUtil.success(data);
        }

        //解密
        try {
            String result = AesCbuUtil.getUserInfo(encryptedData, sessionKey, iv);
            data.put(FrontConst.USERINFO_NAME, result);
            template.opsForValue().set(KeyPrefixConst.USER_INFO + openId, result);
        } catch (Exception e) {
            log.error("decode err");
            e.printStackTrace();
        }
        return ResponseUtil.success(data);
    }
}
