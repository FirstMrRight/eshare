package com.example.ltx.eshare.common.annotation;

import java.lang.annotation.*;

//解密注解
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {
}
