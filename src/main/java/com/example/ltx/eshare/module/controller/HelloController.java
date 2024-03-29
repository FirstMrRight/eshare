package com.example.ltx.eshare.module.controller;

import cn.hutool.core.lang.Assert;
import com.example.ltx.eshare.common.annotation.Encrypt;
import com.example.ltx.eshare.common.enums.ResponseEnum;
import com.example.ltx.eshare.common.model.EncryptConfig;
import com.example.ltx.eshare.common.resp.ResultMessage;
import com.example.ltx.eshare.module.entity.UserDto;
import com.example.ltx.eshare.module.mapper.UserMapper;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author Liutx
 * @date 2020/12/13 19:50
 * @Description
 */
@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EncryptConfig encryptConfig;


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
        System.out.println("111");
        String a = null;
        Preconditions.checkNotNull(a, ResponseEnum.RESULE_DATA_NONE.getMessage());
        Assert.notNull(userId, ResponseEnum.SYSTEM_INNER_ERROR.getMessage());
//        ResponseEnum.DATA_IS_WRONG.assertNotNull(userId);
        return ResultMessage.success(userMapper.getUserById(userId));
    }
}
