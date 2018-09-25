package cn.sweetyhut.ezoa.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 16:24
 */
@Component
public class CheckLogTask {
    @Autowired
    private StringRedisTemplate template;

    /**
     * cron: seconds minutes hours dayofmonth month dayofweek year
     * e.g. "0 0/5 16,20 * * ?"    每天的16点至16：55和20点至20点55分两个时间段内每5分钟一次触发
     */
    @Scheduled(cron = "0 0 4 * * ?")
    public void cron() throws Exception {
        String nowDate = LocalDate.now().minusDays(1).toString();
        String keyPattern = "user:check:" + nowDate + ":*";
        Set<String> keySet = template.keys(keyPattern);
        if (keySet == null) {
            return;
        }

        for (String key : keySet) {
        }
    }
}
