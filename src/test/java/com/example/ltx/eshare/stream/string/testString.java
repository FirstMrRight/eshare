package com.example.ltx.eshare.stream.string;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: LiuTX
 * @Date: 2021/7/12 14:27
 */
public class testString {

    public static void main(String[] args) {
        wrong();
    }

    private static void wrong() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<Integer> subList = list.subList(1, 4);
        System.out.println(subList);
        subList.remove(1);
        System.out.println(list);
        list.add(0);
        try {
            subList.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
