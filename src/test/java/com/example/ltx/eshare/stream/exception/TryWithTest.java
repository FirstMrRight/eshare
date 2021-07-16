package com.example.ltx.eshare.stream.exception;

import java.io.*;

/**
 * @author: LiuTX
 * @date: 2021/7/16 15:32
 * @description:
 */
public class TryWithTest {
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//JDK 7之后增加addSuppressed方法，支持将一个异常附加到另一个异常身上，从而避免异常屏蔽。
    private static void test() throws Exception {
        Connection conn = null;
        try {
            conn = new Connection();
            conn.sendData();
        } finally {
            if (conn != null) {
                conn.close();
            }

        }
    }
}
