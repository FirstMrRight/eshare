package com.example.ltx.eshare.common.annotation;


import java.lang.annotation.*;

//加密注解
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//@Documented

public @interface Encrypt {
}
