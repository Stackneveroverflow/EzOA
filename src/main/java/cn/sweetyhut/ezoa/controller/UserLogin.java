package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.config.MiniProgramConfig;
import cn.sweetyhut.ezoa.constant.WxApiConst;
import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.utils.AesCbuUtil;
import cn.sweetyhut.ezoa.utils.ResponseUtil;
import cn.sweetyhut.ezoa.utils.requestUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * UserLoginController
 *
 * @author Macer
 * @version V1.1 重构
 * @date 2018/09/23 13:44
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserLogin {
    private StringRedisTemplate template;
    private MiniProgramConfig config;

    @Autowired
    public UserLogin(StringRedisTemplate template, MiniProgramConfig config) {
        this.template = template;
        this.config = config;
    }

    @ResponseBody
    @GetMapping("/login")
    public MiniResponse login(String code, String encryptedData, String iv) throws Exception {
        Map<String, Object> data = new HashMap<>();

        //code2session API 成功返回openid和session_key , 失败返回errcode errmsg
        String requestParam = "appid=" + config.getAppId() + "&secret=" + config.getSecretKey() + "&js_code=" + code + "&grant_type=" + WxApiConst.GRANT_TYPE;
        String resJsonString = requestUtil.getForObj(config.getWxCode2SessionApi(), requestParam, String.class);
        JsonObject responseBody = new Gson().fromJson(resJsonString, JsonObject.class);

        //判断code2session是否失败
        if (responseBody.has("errcode")) {
            Integer errCode = responseBody.get("errcode").getAsInt();
            String errMsg = responseBody.get("errmsg").getAsString();
            return ResponseUtil.error(errCode, errMsg);
        }

        String sessionKey = responseBody.get("session_key").getAsString();
        String openId = responseBody.get("openid").getAsString();
        data.put("skey", sessionKey);

        if (encryptedData == null || iv == null) {
            data.put("userinfo", template.opsForValue().get("user:info:" + openId));
            return ResponseUtil.success(data);
        }

        //解密
        try {
            String result = AesCbuUtil.getUserInfo(encryptedData, sessionKey, iv);
            data.put("userinfo", result);
            template.opsForValue().set("user:info:" + openId, result);
        } catch (Exception e) {
            log.error("decode err");
            e.printStackTrace();
        }
        return ResponseUtil.success(data);
    }
}
