package com.example.ltx.eshare.stream;

import lombok.*;

import java.io.Serializable;

/**
 * @Author: LiuTX
 * @Date: 2021/6/30 10:38
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = -9079722457749166858L;
    private String name;
    private Integer age;
    private Double salary;


}
