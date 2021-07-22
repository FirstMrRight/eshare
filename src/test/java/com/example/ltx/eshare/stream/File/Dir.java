package com.example.ltx.eshare.stream.File;

import java.io.File;
import java.math.BigDecimal;

/**
 * @author: LiuTX
 * @date: 2021/7/21 16:37
 * @description:
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("Java中字符流与字节流的区别.md");
        System.out.println(file.exists());
        System.out.println(System.getProperty("user.dir"));

        File absoluteFile = new File("E:\\GitDown\\eshare\\Java中字符流与字节流的区别.md");
        System.out.println(absoluteFile.exists());
        System.out.println(System.getProperty("user.dir"));
    }

}
