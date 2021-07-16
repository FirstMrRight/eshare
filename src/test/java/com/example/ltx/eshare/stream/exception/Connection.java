package com.example.ltx.eshare.stream.exception;

/**
 * @author: LiuTX
 * @date: 2021/7/16 17:00
 * @description:
 */
public class Connection implements AutoCloseable {

    public void sendData() throws Exception{
        System.out.println("正在发送数据");
        throw new Exception("send data");
    }

    @Override
    public void close() throws Exception {
        System.out.println("正在关闭连接");
        throw new Exception("send close");
    }
}
