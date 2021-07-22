package com.example.ltx.eshare.stream.File;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: LiuTX
 * @date: 2021/7/21 11:45
 * @description:
 */
@Slf4j
public class FileBad {
    public static void main(String[] args) throws IOException {
        init();
//        wrong();
        right1();
    }

    private static void init() throws IOException {
        Files.deleteIfExists(Paths.get("hello.txt"));
        //写入一个GBK格式的文件
        Files.write(Paths.get("hello.txt"), "你好hi".getBytes(Charset.forName("GBK")));
        log.info("bytes:{}", Hex.encodeHexString(Files.readAllBytes(Paths.get("hello.txt"))).toUpperCase());
    }

    private static void wrong() throws IOException {
        log.info("charset: {}", Charset.defaultCharset());

        char[] chars = new char[10];
        String content = "";
        try (FileReader fileReader = new FileReader("hello.txt")) {
            int count;
            while ((count = fileReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }
        log.info("result:{}", content);

        Files.write(Paths.get("hello2.txt"), "你好hi".getBytes(Charsets.UTF_8));
        log.info("bytes:{}", Hex.encodeHexString(Files.readAllBytes(Paths.get("hello2.txt"))).toUpperCase());
    }


    private static void right1() throws IOException {

        char[] chars = new char[10];
        String content = "";
        try (FileInputStream fileInputStream = new FileInputStream("hello.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("GBK"))) {
            int count;
            while ((count = inputStreamReader.read(chars)) != -1) {
                content += new String(chars, 0, count);
            }
        }

        log.info("result: {}", content);

        log.info("使用JDK7的特性，读取超过内存的文件时，会OOM");
        log.info("result: {}", Files.readAllLines(Paths.get("hello.txt"), Charset.forName("GBK")).stream().findFirst().orElse(""));
        //Files.lines需要指定正确的编码格式才可以读，读取完成后需要关闭打开的文件
        try (Stream<String> lines = Files.lines(Paths.get("hello2.txt"))) {
            List<String> collect = lines.collect(Collectors.toList());
        }
    }
}
