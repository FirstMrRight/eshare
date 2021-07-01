package com.example.ltx.eshare.stream.jdk8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @Author: LiuTX
 * @Date: 2021/6/25 15:48
 */
@Slf4j
public class ConsumerTest {

    @Test
    public void test_Consumer() {
        //① 使用consumer接口实现方法
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info(s);
            }
        };

        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);
        System.out.println("***********************");

        //② 使用consumer接口实现方法
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        Consumer<String> consumer1 = (s) -> System.out.println(s);
        stream.forEach(consumer1);
    }

    @Test
    public void test() {
        //定义一个方法，执行输出
        Consumer<String> consumer = x -> System.out.println(x);
        //accept:接收参数执行
        consumer.accept("1111");
    }

    @Test
    public void test2() {
        BinaryOperator<Integer> bo = (a, b) -> {
            System.out.println("函数式接口");
            return a + b;
        };
        bo.apply(1, 2);
    }

@Test
    public void test5(){
        Comparator<Integer> comparator = (x,y)->Integer.compare(x,y);
        comparator.compare(1,2);
}

}
