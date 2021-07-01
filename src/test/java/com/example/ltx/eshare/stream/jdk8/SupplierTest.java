package com.example.ltx.eshare.stream.jdk8;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.copier.Copier;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @Author: LiuTX
 * @Date: 2021/6/28 9:30
 */
public class SupplierTest {
    @Test
    public void test_Supplier() {
        //使用Supplier接口实现方法，只有一个Get方法，无参数，返回一个值
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };
        System.out.println(supplier.get());
        System.out.println("********************");

        supplier=()->new Random().nextInt();
        System.out.println(supplier.get());
        System.out.println("********************");

        Supplier<Double> random = Math::random;
        System.out.println(random.get());
    }

    @Test
    public void test_Supplier2(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Optional<Integer> first = integerStream.filter(i -> i > 4).findFirst();
        //optional对象有需要Supplier接口的方法
        //orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        };
        System.out.println(first.orElseGet(supplier));
    }


    @Test
    public void testr2(){
        String dateStr1 = "2016-09-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2021-06-30 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);
        //国同学，这是我们相识的日子
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
    }


}
