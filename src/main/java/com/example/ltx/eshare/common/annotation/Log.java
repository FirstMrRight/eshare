package com.example.ltx.eshare.common.annotation;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Liutx
 * @date 2020/11/21 14:42
 * @Description @Aspect注解表示这个类是一个切面类，@Aspect注解表示这个类是一个切面类
 */
@Aspect
@Component
public class Log {
    /**
     * 定义一个前置通知
     */
    @Before("execution(* com.example..*.*(..))")
    public void log() {
        System.out.println("Before");
    }

}
