package com.example.ltx.eshare.common.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Liutx
 * @date 2020/11/21 21:17
 * @Description
 */

@Aspect
@Component
public class Around {
    @Pointcut("execution(* com.example..*.*(..))")
    public void testAround() {

    }

    @org.aspectj.lang.annotation.Around("testAround()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        if (Objects.nonNull(result)) {
            //输出获取的方法执行结果
            System.out.println(result.toString());
            return result;
        }
        return "不满足条件(瞎写的)";
    }
}
