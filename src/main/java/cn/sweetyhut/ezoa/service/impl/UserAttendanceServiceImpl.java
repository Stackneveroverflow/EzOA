package cn.sweetyhut.ezoa.service.impl;

import cn.sweetyhut.ezoa.constant.FrontConst;
import cn.sweetyhut.ezoa.constant.KeyPrefixConst;
import cn.sweetyhut.ezoa.constant.TimeConst;
import cn.sweetyhut.ezoa.domain.UserLog;
import cn.sweetyhut.ezoa.exception.RedisEmptyValueException;
import cn.sweetyhut.ezoa.service.UserAttendanceService;
import cn.sweetyhut.ezoa.service.UserLogService;
import cn.sweetyhut.ezoa.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户考勤服务
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/30 16:59
 */
@Service
@Slf4j
public class UserAttendanceServiceImpl implements UserAttendanceService {
    private StringRedisTemplate template;
    private UserLogService userLogService;

    @Autowired
    public UserAttendanceServiceImpl(StringRedisTemplate template, UserLogService userLogService) {
        this.template = template;
        this.userLogService = userLogService;
    }

    @Override
    public Map check(String openid, String ssid, String bssid) {
        Map<String, Object> data = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TimeConst.CHECK_PATTERN);
        String nowTime = LocalTime.now().format(formatter);
        String nowDate = LocalDate.now().toString();
        String key = KeyPrefixConst.USER_CHECK + nowDate + ":" + openid;
        template.opsForList().rightPush(key, nowTime);
        String firstTime = template.opsForList().index(key, 0);

        data.put(FrontConst.MESSAGE_NAME, "打卡成功！今日第一次打卡时间 " + firstTime + " ，最后一次打卡时间 " + nowTime);
        return data;
    }

    @Override
    public Map checkStatus(String openid) {
        Map<String, Object> data = new HashMap<>();

        String nowDate = LocalDate.now().toString();
        String key = KeyPrefixConst.USER_CHECK + nowDate + ":" + openid;
        if (!template.hasKey(key)) {
            data.put(FrontConst.MESSAGE_NAME, "今日还未打卡");
            return data;
        }

        String firstTime = template.opsForList().index(key, 0);
        String lastTime = template.opsForList().index(key, -1);

        data.put(FrontConst.MESSAGE_NAME, "今日第一次打卡时间 " + firstTime + " ，最后一次打卡时间 " + lastTime);
        return data;
    }

    @Override
    public List<UserLog> getAttendanceLog(String openid) {
        return userLogService.findByUid(openid);
    }

    @Override
    public void saveWorkHoursDaily() {
        LocalDate logDate = LocalDate.now().minusDays(1);
        String keyPattern = KeyPrefixConst.USER_CHECK + logDate + ":";
        Set<String> keySet = template.keys(keyPattern + "*");
        if (keySet == null || keySet.isEmpty()) {
            throw new RedisEmptyValueException();
        }

        Set<String> openidSet = keySet.parallelStream()
                .map(e -> e.substring(e.lastIndexOf(":") + 1))
                .collect(Collectors.toSet());
        UserLog userLog = new UserLog();
        for (String openid : openidSet) {
            String key = keyPattern + openid;
            userLog.setUid(openid);
            userLog.setLogDate(logDate);

            String firstTime = template.opsForList().index(key, 0);
            String lastTime = template.opsForList().index(key, -1);
            userLog.setWorkHours(TimeUtil.getWorkHours(firstTime, lastTime));

            userLogService.save(userLog);
        }
    }
}
