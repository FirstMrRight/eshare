package com.example.ltx.eshare.common.model;

import lombok.Data;

@Data
public class EncryptModel {
    private String data;
    private String ts;
    private String sign;
    private String key;
}
