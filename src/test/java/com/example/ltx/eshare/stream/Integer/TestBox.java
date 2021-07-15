package com.example.ltx.eshare.stream.Integer;

import java.util.HashMap;

/**
 * @author: LiuTX
 * @date: 2021/7/15 10:27
 * @description:
 */
public class TestBox {
    public static void main(String[] args) {
        HashMap<String, Object> testInteger = new HashMap<>();
        testInteger.put("right", 1);
        testInteger.put("wrong", null);

        Integer wrong = (Integer) testInteger.get("wrong");
        int value = wrong.intValue();
        System.out.println(value);
    }

}
