package cn.sweetyhut.ezoa.controller;

import lombok.extern.slf4j.Slf4j;
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
    @ResponseBody
    @GetMapping("/login")
    public Map login(String code) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("msg", "success");
        return map;
    }
}
