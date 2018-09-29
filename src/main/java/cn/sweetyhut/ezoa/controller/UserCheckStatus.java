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
import java.util.HashMap;
import java.util.Map;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/24 21:18
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserCheckStatus {
    private StringRedisTemplate template;

    @Autowired
    public UserCheckStatus(StringRedisTemplate template) {
        this.template = template;
    }

    @ResponseBody
    @GetMapping("/checkstatus")
    public MiniResponse checkStatus(String openid) {
        Map<String, Object> data = new HashMap<>();

        String nowDate = LocalDate.now().toString();
        String key = "user:check:" + nowDate + ":" + openid;
        if (!template.hasKey(key)) {
            data.put("msg", "今日还未打卡");
            return ResponseUtil.success(data);
        }

        String firstTime = template.opsForList().index(key, 0);
        String lastTime = template.opsForList().index(key, -1);

        data.put("msg", "今日第一次打卡时间 " + firstTime + " ，最后一次打卡时间 " + lastTime);
        return ResponseUtil.success(data);
    }
}
