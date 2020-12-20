package com.example.ltx.eshare.common.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Liutx
 * @date 2020/12/20 11:28
 * @Description
 */
@Data
@Component
@ConfigurationProperties(prefix = "encrypt")
public class EncryptConfig {
    private String privateKey;
    private String publicKey;
    private String charset = "UTF-8";
    private boolean open = true;
    private boolean showLog = false;
}
