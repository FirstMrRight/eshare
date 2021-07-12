package com.example.ltx.eshare.stream.string;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

/**
 * @Author: LiuTX
 * @Date: 2021/7/12 15:42
 */
@Slf4j
public class PointWrong {
    private int x;
    private int y;
    private final String desc;

    public PointWrong(int x, int y, String desc) {
        this.x = x;
        this.y = y;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object obj) {
        PointWrong that = (PointWrong) obj;
        return x == that.x && y == that.y;
    }

    public static void main(String[] args) {
        PointWrong p1 = new PointWrong(1, 2, "a");
        try {
            log.info("p1.equals(null) ? {}", p1.equals(null));
        } catch (Exception ex) {
            log.error(ex.toString());
        }

        Object o = new Object();
        try {
            log.info("p1.equals(expression) ? {}", p1.equals(o));
        } catch (Exception ex) {
            log.error(ex.toString());
        }

        PointWrong p2 = new PointWrong(1, 2, "b");
        log.info("p1.equals(p2) ? {}", p1.equals(p2));

        HashSet<PointWrong> points = new HashSet<>();
        points.add(p1);
        log.info("points.contains(p2) ? {}", points.contains(p2));
    }
}
