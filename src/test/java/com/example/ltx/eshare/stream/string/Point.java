package com.example.ltx.eshare.stream.string;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: LiuTX
 * @Date: 2021/7/12 15:14
 */

@Slf4j
public class Point {
    private int x;
    private int y;
    private final String desc;

    public Point(int x, int y, String desc) {
        this.x = x;
        this.y = y;
        this.desc = desc;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1,2,"a");
        Point p2 = new Point(1,2,"b");
        Point p3 = new Point(1,2,"a");

        //未重写equals方法，默认使用Object，对比引用
        log.info("p1.equlas(p2) ? {}",p1.equals(p2));
        log.info("p1.equlas(p3) ? {}",p1.equals(p3));
    }
}

