package com.example.ltx.eshare.module.controller;

import com.example.ltx.eshare.common.enums.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liutx
 * @date 2020/12/13 19:50
 * @Description
 */
@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class HelloController {
    @GetMapping("hello")
    @ApiOperation(value = "用户测试",notes = "用户测试notes")
    public String hello(String test) {
        ResponseEnum.DATA_IS_WRONG.assertNotNull(test);
        return test;
    }
}
