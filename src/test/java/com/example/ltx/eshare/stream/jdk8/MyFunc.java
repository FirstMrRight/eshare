package com.example.ltx.eshare.stream.jdk8;

/**
 * @Author: LiuTX
 * @Date: 2021/7/2 16:07
 */
@FunctionalInterface
public interface MyFunc<T, R> {
    R getValue(T t1, T t2);
}