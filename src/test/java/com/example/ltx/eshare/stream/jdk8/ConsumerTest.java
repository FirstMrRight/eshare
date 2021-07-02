package com.example.ltx.eshare.stream.jdk8;

import com.example.ltx.eshare.stream.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: LiuTX
 * @Date: 2021/6/25 15:48
 */
@Slf4j
public class ConsumerTest {


    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.55),
            new Employee("王五", 60, 6666.66),
            new Employee("赵六", 8, 7777.77),
            new Employee("田七", 58, 3333.33));

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
    public void test5() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        comparator.compare(1, 2);
    }

    @Test
    public void test6() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            }
            return Integer.compare(e1.getAge(), e2.getAge());
        });
        employees.stream().forEach(System.out::println);
    }

}
