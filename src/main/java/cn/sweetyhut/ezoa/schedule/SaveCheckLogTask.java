package cn.sweetyhut.ezoa.schedule;

import cn.sweetyhut.ezoa.exception.RedisEmptyValueException;
import cn.sweetyhut.ezoa.service.UserAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 16:24
 */
@Component
@Slf4j
public class SaveCheckLogTask {
    private UserAttendanceService userAttendanceService;

    @Autowired
    public SaveCheckLogTask(UserAttendanceService userAttendanceService) {
        this.userAttendanceService = userAttendanceService;
    }

    /**
     * 每天早上6点将前一天的工时记录到数据库中
     * <p>
     * cron: seconds minutes hours dayofmonth month dayofweek year
     * e.g. "0 0/5 16,20 * * ?"    每天的16点至16：55和20点至20点55分两个时间段内每5分钟一次触发
     */
    @Scheduled(cron = "0 0 6 * * ?")
    public void cron() {
        try {
            userAttendanceService.saveWorkHoursDaily();
            log.info("SaveCheckLogTask Done!!! At " + LocalDate.now());
        } catch (RedisEmptyValueException e) {
            log.info("Get Empty Back From Redis At " + LocalDate.now());
        } finally {
            this.userAttendanceService = null;
        }
    }
}
