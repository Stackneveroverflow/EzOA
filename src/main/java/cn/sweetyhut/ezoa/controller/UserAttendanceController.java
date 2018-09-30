package cn.sweetyhut.ezoa.controller;

import cn.sweetyhut.ezoa.constant.FrontConst;
import cn.sweetyhut.ezoa.domain.UserLog;
import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.service.UserAttendanceService;
import cn.sweetyhut.ezoa.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try {
            Map data = userAttendanceService.check(openid, ssid, bssid);
            return ResponseUtil.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error(e.hashCode(), e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/checkstatus")
    public MiniResponse checkStatus(String openid) {
        try {
            Map data = userAttendanceService.checkStatus(openid);
            return ResponseUtil.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error(e.hashCode(), e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/getchecklog")
    public MiniResponse getCheckLog(String openid) {
        try {
            List<Map> data = new ArrayList<>();
            List<UserLog> list = userAttendanceService.getAttendanceLog(openid);
            for (UserLog userLog : list) {
                Map<String, Object> map = new HashMap<>();
                map.put(FrontConst.LOG_DATE_NAME, userLog.getLogDate());
                map.put(FrontConst.WORK_HOURS_NAME, userLog.getWorkHours());
                data.add(map);
            }
            return ResponseUtil.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.error(e.hashCode(), e.getMessage());
        }
    }
}
