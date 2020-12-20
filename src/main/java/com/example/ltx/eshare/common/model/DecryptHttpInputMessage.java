package com.example.ltx.eshare.common.model;

import com.example.ltx.eshare.common.utils.Base64Util;
import com.example.ltx.eshare.common.utils.RSAUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author Liutx
 * @date 2020/12/20 15:04
 * @Description
 */
public class DecryptHttpInputMessage implements HttpInputMessage {
    private final HttpHeaders headers;
    private final InputStream body;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage, String privateKey, String charset, boolean showLog) throws Exception {
        if (StringUtils.isEmpty(privateKey)) {
            throw new IllegalArgumentException("privateKey is null");
        } else {
            this.headers = inputMessage.getHeaders();
            String content = (String)(new BufferedReader(new InputStreamReader(inputMessage.getBody()))).lines().collect(Collectors.joining(System.lineSeparator()));
            String decryptBody;
            Logger log = LoggerFactory.getLogger(this.getClass());
            if (content.startsWith("{")) {
                log.info("Unencrypted without decryption:{}", content);
                decryptBody = content;
            } else {
                StringBuilder json = new StringBuilder();
                content = content.replaceAll(" ", "+");
                if (!StringUtils.isEmpty(content)) {
                    String[] contents = content.split("\\|");
                    String[] var9 = contents;
                    int var10 = contents.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        String value = var9[var11];
                        value = new String(RSAUtil.decrypt(Base64Util.decode(value), privateKey), charset);
                        json.append(value);
                    }
                }

                decryptBody = json.toString();
                if (showLog) {
                    log.info("Encrypted data received：{},After decryption：{}", content, decryptBody);
                }
            }

            this.body = new ByteArrayInputStream(decryptBody.getBytes());
        }
    }

    public InputStream getBody() {
        return this.body;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }
}
