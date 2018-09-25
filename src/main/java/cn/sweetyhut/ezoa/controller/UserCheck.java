package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.config.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/24 21:28
 */
@RestController
@RequestMapping("/ezoa")
@Slf4j
public class UserCheck {
    @Autowired
    private StringRedisTemplate template;

    @ResponseBody
    @GetMapping("/check")
    public Map check(String openid, String ssid, String bssid) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        String nowTime = LocalTime.now().toString();
        String nowDate = LocalDate.now().toString();
        String key = "user:check:" + nowDate + ":" + openid;
        log.warn("checkkey:" + key);
        template.opsForList().rightPush(key, nowTime);
        String firstTime = template.opsForList().index(key, 0);

        map.put("code", WechatConfig.CODE_OK);
        map.put("msg", "ok");
        data.put("msg", "打卡成功！今日第一次打卡时间 " + firstTime + " ，最后一次打卡时间 " + nowTime);
        map.put("data", data);
        return map;
    }
}
