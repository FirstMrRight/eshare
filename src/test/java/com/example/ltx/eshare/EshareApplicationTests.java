package com.example.ltx.eshare;

import com.example.ltx.eshare.common.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshareApplicationTests {

    @Autowired
    private RedisService redisUtil;


    @Test
    void contextLoads() {
        Object user_uid_test = redisUtil.getCacheObject("USER_UID_TEST:1");
    }

}
