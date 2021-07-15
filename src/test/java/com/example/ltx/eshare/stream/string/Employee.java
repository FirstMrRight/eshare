package com.example.ltx.eshare.stream.string;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: LiuTX
 * @Date: 2021/7/13 8:51
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Employee extends PointWrong{

    private String company;

    public Employee(int x, int y, String desc) {
        super(x, y, desc);
    }
}
