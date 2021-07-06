package com.example.ltx.eshare.stream.jdk8;

import com.example.ltx.eshare.stream.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.CompareTo;

import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

/**
 * @Author: LiuTX
 * @Date: 2021/7/2 16:08
 */

@Slf4j
public class TestClass {

    public void operate(Long num1, Long num2, MyFunc<Long, Long> myFunc) {
        Long value = myFunc.getValue(num1, num2);
        System.out.println(value);
    }

    @Test
    public void test4() {

        operate(100L, 299L, new MyFunc<Long, Long>() {
            @Override
            public Long getValue(Long t1, Long t2) {
                //匿名内部类实现MyFun接口
                return t1 + t2;
            }
        });

        operate(100L, 299L, (x, y) -> x + y);


        log.debug("===================乘法实现=====================");

        //乘法的接口实现
        operate(100L, 200L, new MyFunc<Long, Long>() {
            @Override
            public Long getValue(Long t1, Long t2) {
                return t1 * t2;
            }
        });

        operate(100L, 200l, (X, Y) -> X * Y);
    }

    @Test
    public void test5() {
        BinaryOperator<Double> bo = (x, y) -> Math.pow(x, y);

        Double apply = bo.apply(1.1, 2.1);

        BinaryOperator<Double> bos = Math::pow;
        bos.apply(apply, apply);
    }
}