package com.example.ltx.eshare.stream.basic;

import lombok.Data;

/**
 * @Author: LiuTX
 * @Date: 2021/7/1 16:22
 * 匿名内部类
 */
@Data
public abstract class Bird {
    private String name;

    public abstract int fly();

}
