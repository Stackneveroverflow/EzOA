package cn.sweetyhut.ezoa;

import cn.sweetyhut.ezoa.domain.UserLog;
import cn.sweetyhut.ezoa.service.UserLogServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EzoaApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserLogServer userLogServer;

    @Test
    public void contextLoads() {
    }

    @Test
    public void redisTest() {
        redisTemplate.opsForValue().set("key0", "hello ");
        redisTemplate.opsForValue().set("key1", "world!");
        String result = redisTemplate.opsForValue().get("key0") + redisTemplate.opsForValue().get("key1");
        Assert.assertNotNull(result);
        log.info(result);
    }

    @Test
    public void daoTest() {
        UserLog userLog = userLogServer.findByUid("007");
        Assert.assertNotNull(userLog);
        log.info(userLog.getWorkHours().toString());
    }

    @Test
    public void controllerTest() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/ezoa/login");
        request.addParameter("code", "1lk3j2k1ljlk");
    }

}
