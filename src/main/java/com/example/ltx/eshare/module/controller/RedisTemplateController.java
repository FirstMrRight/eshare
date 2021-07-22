package com.example.ltx.eshare.module.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ltx.eshare.common.constant.BusinessConstant;
import com.example.ltx.eshare.common.redis.RedisUtil;
import com.example.ltx.eshare.common.utils.LocalDateUtils;
import com.example.ltx.eshare.module.entity.User;
import com.example.ltx.eshare.module.entity.Users;
import com.example.ltx.eshare.security.config.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("redistemplate")
@Slf4j
public class RedisTemplateController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisUtil redisUtil;
//    @Autowired
//    private RedisTemplate<String, User> userRedisTemplate;
//    @Autowired
//    private RedisTemplate<String, Long> countRedisTemplate;


    @PostConstruct
    public void init() throws JsonProcessingException {
        redisTemplate.opsForValue().set("redisTemplate", new Users("zhuye", 36));
        stringRedisTemplate.opsForValue().set("stringRedisTemplate", objectMapper.writeValueAsString(new Users("zhuye", 36)));
        redisUtil.setCacheObject(BusinessConstant.REDIS_RELATED.PREFIX + LocalDateUtils.getStartTimeOfDayStr(), new Users("LiuTx", 36));
    }
    @GetMapping("wrong")
    public void wrong() {
        log.info("redisTemplate get {}", redisTemplate.opsForValue().get("stringRedisTemplate"));
        log.info("stringRedisTemplate get {}", stringRedisTemplate.opsForValue().get("redisTemplate"));
    }

    @GetMapping("right")
    public void right() throws JsonProcessingException {
        Users userFromRedisTemplate = (Users) redisTemplate.opsForValue().get("redisTemplate");
        log.info("redisTemplate get {}", userFromRedisTemplate);
        Users userFromStringRedisTemplate = objectMapper.readValue(stringRedisTemplate.opsForValue().get("stringRedisTemplate"), Users.class);
        log.info("stringRedisTemplate get {}", userFromStringRedisTemplate);
        Users users= (Users)redisUtil.getCacheObject("USER_UID_2021-07-22 00:00:00");
        log.info("USER_UID_2021-07-22 00:00:00:{}", users);
    }

//    @GetMapping("right2")
//    public void right2() {
//        User user = new User("zhuye", 36);
//        userRedisTemplate.opsForValue().set(user.getName(), user);
//        User userFromRedis = userRedisTemplate.opsForValue().get(user.getName());
//        log.info("userRedisTemplate get {} {}", userFromRedis, userFromRedis.getClass());
//        log.info("stringRedisTemplate get {}", stringRedisTemplate.opsForValue().get(user.getName()));
//    }

//    @GetMapping("wrong2")
//    public void wrong2() {
//        String key = "testCounter";
//        countRedisTemplate.opsForValue().set(key, 1L);
//        log.info("{} {}", countRedisTemplate.opsForValue().get(key), countRedisTemplate.opsForValue().get(key) instanceof Long);
//        Long l1 = getLongFromRedis(key);
//        countRedisTemplate.opsForValue().set(key, Integer.MAX_VALUE + 1L);
//        log.info("{} {}", countRedisTemplate.opsForValue().get(key), countRedisTemplate.opsForValue().get(key) instanceof Long);
//        Long l2 = getLongFromRedis(key);
//        log.info("{} {}", l1, l2);
//    }
//
//    private Long getLongFromRedis(String key) {
//        Object o = countRedisTemplate.opsForValue().get(key);
//        if (o instanceof Integer) {
//            return ((Integer) o).longValue();
//        }
//        if (o instanceof Long) {
//            return (Long) o;
//        }
//        return null;
//    }
}
