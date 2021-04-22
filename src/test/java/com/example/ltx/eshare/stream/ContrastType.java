package com.example.ltx.eshare.stream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Liutx
 * @date 2021/4/11 14:43
 * @Description
 */

@Slf4j
public class ContrastType {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        //在缓存区内，7
//        log.info("\nInteger a = 127;\n" + "Integer b = 127;\n" + "a==b ? {}", a == b);

        Integer c = 128;
        Integer d = 128;
        //Integer.valueOf(128); 自动装箱,128不在缓存范围内，所以返回的不是基本类型，而是Integer类型的两个对象
        log.info("\nInteger a = 128;\n" + "Integer b = 128;\n" + "c==d ? {}", c == d);


        Integer e = 127;
        Integer f = new Integer(127);
//        log.info("\nInteger e = 127;\n" + "Integer f = 127;\n" + "e==f ? {}", e == f);

        Integer g = new Integer(127);
        Integer h = new Integer(127);
        log.info("\nInteger g = 127;\n" + "Integer h = 127;\n" + "g==h ? {}", g == h);

        Integer i = 128;
        int j = 128;
        log.info("\nInteger i = 127;\n" + "Integer j = 127;\n" + "i==j ? {}", i == j);

    }
}
