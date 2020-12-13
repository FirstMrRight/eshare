package com.example.ltx.eshare.common.aop;

import org.springframework.stereotype.Component;

/**
 * @author Liutx
 * @date 2020/11/21 14:38
 * @Description 使用Component把这个类纳入到Spring容器中
 */

@Component
public class Person {
    public void sayHi() {
        System.out.println("Hello");
    }
}
