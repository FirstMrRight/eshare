package com.example.ltx.eshare;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.ltx.eshare.common.redis.RedisService;
import com.example.ltx.eshare.module.entity.Role;
import com.example.ltx.eshare.module.entity.User;
import org.checkerframework.checker.units.qual.A;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class EshareApplicationTests {

    @Autowired
    private RedisService redisUtil;


    @Test
    void contextLoads() {
        ArrayList<Role> arrayList = new ArrayList();
        arrayList.add(new Role().setId(1).setName("11"));
        redisUtil.setCacheObject("test",arrayList);

        Object user_uid_test = redisUtil.getCacheObject("test");
        JSON parse = JSONUtil.parse(user_uid_test);
        System.out.println(parse);
    }

}
