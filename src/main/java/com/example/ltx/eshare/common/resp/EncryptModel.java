package com.example.ltx.eshare.common.resp;

import lombok.Data;

/**
 * @author Liutx
 * @date 2020/12/20 19:23
 * @Description
 */
@Data
public class EncryptModel {
    private String data;
    private String ts;
    private String sign;
    private String key;
}
