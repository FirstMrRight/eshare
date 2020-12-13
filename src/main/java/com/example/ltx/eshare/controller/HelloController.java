package com.example.ltx.eshare.controller;

import com.example.ltx.eshare.common.enums.ResponseEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liutx
 * @date 2020/12/13 19:50
 * @Description
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String test){
        ResponseEnum.DATA_IS_WRONG.assertNotNull(test);
        return test;
    }
}
