package com.example.ltx.eshare.module.controller;

import cn.hutool.core.lang.Assert;
import com.example.ltx.eshare.common.annotation.Encrypt;
import com.example.ltx.eshare.common.enums.ResponseEnum;
import com.example.ltx.eshare.common.enums.ResultCode;
import com.example.ltx.eshare.common.exception.BusinessException;
import com.example.ltx.eshare.common.model.EncryptConfig;
import com.example.ltx.eshare.common.resp.ResultMessage;
import com.example.ltx.eshare.module.entity.User;
import com.example.ltx.eshare.module.entity.UserDto;
import com.example.ltx.eshare.module.mapper.UserMapper;
import com.example.ltx.eshare.module.service.UserService;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Liutx
 * @date 2020/12/13 19:50
 * @Description
 */
@Slf4j
@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptConfig encryptConfig;

    //类被加载时执行一次
    static {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> log.error("Thread {} got exception", thread, throwable));
    }

    @GetMapping("hello")
    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    public String hello(String test) {
        ResponseEnum.DATA_IS_WRONG.assertNotNull(test);
        return "hello";
    }

    @GetMapping("db/hello")
    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    public String dbHello(String test) {
        ResponseEnum.DATA_IS_WRONG.assertNotNull(test);
        return "dbHello";
    }

    @GetMapping("admin/hello")
    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    public String adminHello(String test) {
        ResponseEnum.DATA_IS_WRONG.assertNotNull(test);
        return "adminHello";
    }

    @Encrypt
    @GetMapping("user/hello")
    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    public String userHello(@PathParam("test") String test) {
        System.out.println(test);
        return test;
    }

    @Encrypt
    @GetMapping("user/token")
    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    public List<UserDto> findUserByToken(@RequestParam("id") Integer userId) {
        ResponseEnum.DATA_IS_WRONG.assertNotNull(userId);
        return userMapper.getUserById(userId);
    }

    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    @GetMapping("exception")
    public ResultMessage exceptionTest(@RequestParam("id") Integer userId) {
        String a = null;
        Preconditions.checkNotNull(a, ResponseEnum.RESULE_DATA_NONE.getMessage());
        Assert.notNull(userId, ResponseEnum.SYSTEM_INNER_ERROR.getMessage());
        return ResultMessage.success(userMapper.getUserById(userId));
    }


    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    @PostMapping("user")
    public ResultMessage user(@RequestBody User user, HttpServletRequest request) {
        ResponseEnum.USER_NOT_EXIST_OR_ERROR.assertEquals(true, userService.createUser(user));
        return ResultMessage.success();
    }

    @GetMapping("optional")
    public ResultMessage optionalTest(@RequestParam("id") Integer userId) {
        User user = userService.getUser(userId);
        Optional<User> optionalUser = Optional.ofNullable(user);
        optionalUser.ifPresent(value -> log.debug(value.getUsername()));
        User unknown = Optional.ofNullable(user).orElse(new User(0, "Unknown"));
        log.info(unknown.getUsername());

        User unknownGet = Optional.ofNullable(userService.getUser(userId)).orElseGet(() -> new User(0, "UnknownGet"));
        log.info(unknownGet.getUsername());
        List<User> userList = new ArrayList<>(2);
        userList.add(unknown);
        userList.add(unknownGet);

        List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
        Optional<List<String>> listOptional = Optional.of(companyNames);
        int size = listOptional.map(List::size).orElse(0);
        log.info(String.valueOf(size));
        return ResultMessage.success().setData(userList);
    }

    /**
     * 错误的零
     *
     * @param test 测试
     * @return int
     */
    @GetMapping("wrong")
    public int wrongNull(@RequestParam(value = "test", defaultValue = "1111") String test) {
        return wrongMethod(test.charAt(0) == '1' ? null : new FooService(),
                test.charAt(1) == '1' ? null : 1,
                test.charAt(2) == '1' ? null : "OK",
                test.charAt(3) == '1' ? null : "OK").size();
    }

    private List<String> wrongMethod(FooService fooService, Integer i, String s, String t) {
        log.info("result {} {} {} {}", i + 1, s.equals("OK"), s.equals(t),
                new ConcurrentHashMap<String, String>().put(null, null));
        if (fooService.getBarService().bar().equals("OK"))
            log.info("OK");
        return null;
    }

    class FooService {
        @Getter
        private BarService barService;

    }

    class BarService {
        String bar() {
            return "OK";
        }
    }


    /**
     * 设置保底处理程序
     *
     * @throws InterruptedException 多线程执行抛出异常时，会被中断
     */
    @GetMapping("execute")
    public void execute() throws InterruptedException {

        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryBuilder()
                .setNameFormat(prefix + "%d")
                .setUncaughtExceptionHandler((thread, throwable) -> log.error("ThreadPool {} got exception", thread, throwable))
                .get());
        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.execute(() -> {
            if (i == 5) throw new RuntimeException("error");
            log.info("I'm done : {}", i);
        }));

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }


    /**
     * 异常被生吞
     *
     * @throws InterruptedException
     */
    @GetMapping("submit")
    public void submit() throws InterruptedException {

        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryBuilder().setNameFormat(prefix + "%d").get());
        IntStream.rangeClosed(1, 10).forEach(i -> threadPool.submit(() -> {
            if (i == 5) throw new RuntimeException("error");
            log.info("I'm done : {}", i);
        }));

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @GetMapping("submitright")
    public void submitRight() throws InterruptedException {

        String prefix = "test";
        ExecutorService threadPool = Executors.newFixedThreadPool(1, new ThreadFactoryBuilder().setNameFormat(prefix + "%d").get());

        List<Future> tasks = IntStream.rangeClosed(1, 10).mapToObj(i -> threadPool.submit(() -> {
            if (i == 5) throw new RuntimeException("error");
            log.info("I'm done : {}", i);
        })).collect(Collectors.toList());

        tasks.forEach(task -> {
            try {
                task.get();
            } catch (Exception e) {
                log.error("Got exception", e);
            }
        });
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }
}
