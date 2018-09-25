package cn.sweetyhut.ezoa;

import cn.sweetyhut.ezoa.domain.UserLog;
import cn.sweetyhut.ezoa.service.UserLogServer;
import lombok.extern.slf4j.Slf4j;
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
    private UserLogServer userLogServer;

    @Test
    public void test() {
        UserLog userLog = new UserLog();
        userLog.setUid("oLLRV40xRwyteNe2Aa1803K6Xz14");
        userLog.setWorkHours(BigDecimal.valueOf(9.2));
        userLog.setLogDate(LocalDate.now());
        log.warn(userLog.toString());
        userLogServer.save(userLog);
        List<UserLog> list = userLogServer.findByUid("oLLRV40xRwyteNe2Aa1803K6Xz14");
        log.warn(list.toString());
    }

    @Test
    public void test2() {
        log.warn(userLogServer.findByUidAndDate("oLLRV40xRwyteNe2Aa1803K6Xz14", LocalDate.now()).getWorkHours().toString());
        UserLog userLog = new UserLog();
        userLog.setUid("oLLRV40xRwyteNe2Aa1803K6Xz14");
        userLog.setWorkHours(BigDecimal.valueOf(8));
        userLog.setLogDate(LocalDate.now());
        log.warn(userLogServer.updateWorkHours(userLog).toString());
    }

}
