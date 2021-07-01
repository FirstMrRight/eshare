package com.example.ltx.eshare.stream.jdk8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: LiuTX
 * @Date: 2021/6/28 16:02
 */
public class PredicateTest {
    @Test
    public void test_Predicate() {
        //使用Predicate接口实现方法,只有一个test方法，传入一个参数，返回一个bool值
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer > 5) {
                    return true;
                }
                return false;
            }
        };

        System.out.println(predicate.test(6));
        System.out.println("********************");

        predicate = (t) -> t > 5;
        System.out.println(predicate.test(1));
        System.out.println("********************");
    }

    @Test
    public void test_Predicate2() {
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer > 5) {
                    return true;
                }
                return false;
            }
        };
        Stream<Integer> stream = Stream.of(1, 23, 3, 4, 5, 56, 6, 6);
//        List<Integer> list = stream.filter(predicate).collect(Collectors.toList());

        List<Integer> lists = stream.filter(i -> i > 5).collect(Collectors.toList());
//        list.forEach(System.out::println);
        lists.forEach(System.out::println);
    }

}
