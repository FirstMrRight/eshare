package com.example.ltx.eshare.stream;

import cn.hutool.core.io.LineHandler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: LiuTX
 * @Date: 2021/6/29 15:06
 */
public class FunctionTest {

    @Test
    public void test_Function() {
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                //获取字符串的长度
                return s.length();
            }
        };

        Stream<String> stream = Stream.of("aaa", "bbbbb", "ccccccv");
        Stream<Integer> stream1 = stream.map(function);
        stream1.forEach(System.out::println);
        System.out.println("********************");

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("aaaaaaaa");
        stringArrayList.add("bbbbb");
        stringArrayList.add("ccc");
        stringArrayList.add("dd");
        //Stream.map()将当前类型转化为其他类型
        //
        List<Integer> collect = stringArrayList.stream().map(String::length).collect(Collectors.toList());
        System.out.println(collect);
    }
}
