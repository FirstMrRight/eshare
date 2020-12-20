package com.example.ltx.eshare.module.controller;

import com.example.ltx.eshare.common.annotation.Decrypt;
import com.example.ltx.eshare.common.annotation.Encrypt;
import com.example.ltx.eshare.common.enums.ResponseEnum;
import com.example.ltx.eshare.common.model.EncryptConfig;
import com.example.ltx.eshare.module.entity.Role;
import com.example.ltx.eshare.module.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Decrypt
    @GetMapping("user/token")
    @ApiOperation(value = "用户测试", notes = "用户测试notes")
    public List<Role> findUserByToken(Integer userId) {
        ResponseEnum.DATA_IS_WRONG.assertNotNull(userId);
        return userMapper.getUserRolesById(userId);
    }
}
