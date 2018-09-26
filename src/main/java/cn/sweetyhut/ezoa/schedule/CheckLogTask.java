package cn.sweetyhut.ezoa.schedule;

import cn.sweetyhut.ezoa.controller.UserCheck;
import cn.sweetyhut.ezoa.domain.UserLog;
import cn.sweetyhut.ezoa.service.UserLogServer;
import cn.sweetyhut.ezoa.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 16:24
 */
@Component
@Slf4j
public class CheckLogTask {
    private StringRedisTemplate template;
    private UserLogServer userLogServer;

    @Autowired
    public CheckLogTask(StringRedisTemplate template, UserLogServer userLogServer) {
        this.template = template;
        this.userLogServer = userLogServer;
    }

    /**
     * 每天早上6点将前一天的工时记录到数据库中
     * <p>
     * cron: seconds minutes hours dayofmonth month dayofweek year
     * e.g. "0 0/5 16,20 * * ?"    每天的16点至16：55和20点至20点55分两个时间段内每5分钟一次触发
     */
    @Scheduled(cron = "0 0 6 * * ?")
    public void cron() throws Exception {
        LocalDate logDate = LocalDate.now().minusDays(1);
        String keyPattern = UserCheck.USER_CHECK_KEY + logDate + ":";
        Set<String> keySet = template.keys(keyPattern + "*");
        if (keySet == null) {
            log.info("keySet is null at " + LocalDateTime.now());
            return;
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

            userLogServer.save(userLog);
        }

        log.info("CheckLogTask done at " + LocalDateTime.now());
    }
}
