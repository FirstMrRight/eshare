package com.example.ltx.eshare.stream;

public enum StatusEnum {
    CREATED(1000, "已创建"),
    PAID(1001, "已支付"),
    FINISHED(1002, "已完成");

    private final Integer status;
    private final String desc;


    StatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
