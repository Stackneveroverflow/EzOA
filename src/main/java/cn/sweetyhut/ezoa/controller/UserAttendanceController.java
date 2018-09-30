package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.service.UserAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/30 18:06
 */
@RestController
@RequestMapping("/user")
public class UserAttendanceController {
    private UserAttendanceService userAttendanceService;

    @Autowired
    public UserAttendanceController(UserAttendanceService userAttendanceService) {
        this.userAttendanceService = userAttendanceService;
    }

    @ResponseBody
    @GetMapping("/check")
    public MiniResponse check(String openid, String ssid, String bssid) {
        return userAttendanceService.check(openid, ssid, bssid);
    }

    @ResponseBody
    @GetMapping("/checkstatus")
    public MiniResponse checkStatus(String openid) {
        return userAttendanceService.checkStatus(openid);
    }
}
