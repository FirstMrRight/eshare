package com.example.ltx.eshare.module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LiuTX
 * @date: 2021/7/22 10:54
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
    private String name;
    private int age;
}
