package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.config.WechatConfig;
import cn.sweetyhut.ezoa.utils.AesCbuUtil;
import cn.sweetyhut.ezoa.utils.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 13:44
 */
@RestController
@RequestMapping("/ezoa")
@Slf4j
public class UserLogin {
    @Autowired
    private StringRedisTemplate template;

    @ResponseBody
    @GetMapping("/login")
    public Map login(String code, String encryptedData, String iv) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        //code2session API
        String requestParam = "appid=" + WechatConfig.APP_ID + "&secret=" + WechatConfig.SECRET_KEY + "&js_code=" + code + "&grant_type=" + WechatConfig.GRANT_TYPE;
        String requestR = HttpRequest.sendGet(WechatConfig.WX_CODE2SESSION_HTTP, requestParam);
        JSONObject jsonObject = JSONObject.fromObject(requestR);

        String sessionKey = jsonObject.getString("session_key");
        String openId = jsonObject.getString("openid");
        Integer errcode = Integer.valueOf(jsonObject.get("errcode").toString());
        String errMsg = jsonObject.getString("errMsg");

        map.put("code", errcode);
        map.put("msg", errMsg);
        if (!errcode.equals(WechatConfig.ERRCODE_OK)) {
            return map;
        }

        data.put("skey", sessionKey);

        if (encryptedData == null || iv == null) {
            data.put("userinfo", template.opsForValue().get("user:info:" + openId));
            map.put("data", data);
            return map;
        }

        try {
            JSONObject result = AesCbuUtil.getUserInfo(encryptedData, sessionKey, iv);
            Map<String, String> userInfo = new HashMap<>();
            mapPut(userInfo, result, "openId");
            mapPut(userInfo, result, "nickName");
            mapPut(userInfo, result, "gender");
            mapPut(userInfo, result, "city");
            mapPut(userInfo, result, "province");
            mapPut(userInfo, result, "country");
            mapPut(userInfo, result, "avatarUrl");
            data.put("userinfo", userInfo);
            template.opsForValue().set("user:info:" + openId, userInfo.toString());
        } catch (Exception e) {
            log.error("decode err");
            e.printStackTrace();
        }
        map.put("data", data);
        return map;
    }

    private void mapPut(Map<String, String> map, JSONObject jsonObject, String s) {
        if (jsonObject == null) {
            return;
        }
        if (jsonObject.containsKey(s)) {
            map.put(s, jsonObject.getString(s));
        } else {
            log.warn(s + " do not exist!!!");
        }
    }
}
