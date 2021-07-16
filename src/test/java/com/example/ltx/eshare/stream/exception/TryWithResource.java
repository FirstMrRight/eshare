package com.example.ltx.eshare.stream.exception;

/**
 * @author: LiuTX
 * @date: 2021/7/16 17:02
 * @description:
 */
public class TryWithResource {
    public static void main(String[] args) {
        try (Connection conn = new Connection()) {
            conn.sendData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
