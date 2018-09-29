package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/user")
@Slf4j
public class UserCheck {
    public static final String USER_CHECK_KEY = "user:check:";
    private StringRedisTemplate template;

    @Autowired
    public UserCheck(StringRedisTemplate template) {
        this.template = template;
    }

    @ResponseBody
    @GetMapping("/check")
    public MiniResponse check(String openid, String ssid, String bssid) {
        Map<String, Object> data = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String nowTime = LocalTime.now().format(formatter);
        String nowDate = LocalDate.now().toString();
        String key = USER_CHECK_KEY + nowDate + ":" + openid;
        template.opsForList().rightPush(key, nowTime);
        String firstTime = template.opsForList().index(key, 0);

        data.put("msg", "打卡成功！今日第一次打卡时间 " + firstTime + " ，最后一次打卡时间 " + nowTime);
        return ResponseUtil.success(data);
    }
}
