package cn.sweetyhut.ezoa;

import cn.sweetyhut.ezoa.domain.UserLog;
import cn.sweetyhut.ezoa.response.MiniResponse;
import cn.sweetyhut.ezoa.schedule.SaveCheckLogTask;
import cn.sweetyhut.ezoa.service.UserLogService;
import cn.sweetyhut.ezoa.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EzoaApplicationTests {
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private SaveCheckLogTask saveCheckLogTask;

    @Test
    public void test() {
        UserLog userLog = new UserLog();
        userLog.setUid("oLLRV40xRwyteNe2Aa1803K6Xz14");
        userLog.setWorkHours(BigDecimal.valueOf(9.2));
        userLog.setLogDate(LocalDate.now());
        log.warn(userLog.toString());
        userLogService.save(userLog);
        List<UserLog> list = userLogService.findByUid("oLLRV40xRwyteNe2Aa1803K6Xz14");
        log.warn(list.toString());
    }

    @Test
    public void test2() {
        log.warn(userLogService.findByUidAndDate("oLLRV40xRwyteNe2Aa1803K6Xz14", LocalDate.now()).getWorkHours().toString());
        UserLog userLog = new UserLog();
        userLog.setUid("oLLRV40xRwyteNe2Aa1803K6Xz14");
        userLog.setWorkHours(BigDecimal.valueOf(8));
        userLog.setLogDate(LocalDate.now());
        log.warn(userLogService.updateWorkHours(userLog).toString());
    }

    @Test
    public void testSchedule() {
        try {

            saveCheckLogTask.cron();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMini() {
        MiniResponse miniResponse = ResponseUtil.success(null);
        Assert.assertNotNull(miniResponse);
    }

}
