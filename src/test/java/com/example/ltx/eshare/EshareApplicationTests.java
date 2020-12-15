package com.example.ltx.eshare;

import cn.hutool.json.JSONUtil;
import com.example.ltx.eshare.common.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshareApplicationTests {

    @Autowired
    private RedisUtil redisUtil;


    @Test
    void contextLoads() {
        redisUtil.get("USER_UID:1");
    }

}
