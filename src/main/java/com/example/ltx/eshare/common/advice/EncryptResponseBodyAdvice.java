package com.example.ltx.eshare.common.advice;

import com.alibaba.fastjson.JSON;
import com.example.ltx.eshare.common.annotation.Encrypt;
import com.example.ltx.eshare.common.model.EncryptConfig;
import com.example.ltx.eshare.common.utils.Base64Util;
import com.example.ltx.eshare.common.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * @author Liutx
 * @date 2020/12/20 15:05
 * @Description
 */

@Slf4j
@RestControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final ThreadLocal<Boolean> ENCRYPT_LOCAL = new ThreadLocal<>();
    private boolean encrypt;

    @Autowired
    private EncryptConfig encryptConfig;

    public EncryptResponseBodyAdvice() {
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Annotation[] annotations = returnType.getDeclaringClass().getAnnotations();
        if (annotations.length > 0 && this.encryptConfig.isOpen()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Encrypt) {
                    return this.encrypt = true;
                }
            }
        }
        return this.encrypt = Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(Encrypt.class) && this.encryptConfig.isOpen();
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Boolean status = ENCRYPT_LOCAL.get();
        if (null != status && !status) {
            ENCRYPT_LOCAL.remove();
        } else {
            if (this.encrypt) {
                String publicKey = this.encryptConfig.getPublicKey();

                try {
                    String content = JSON.toJSONString(body);
                    if (!StringUtils.hasText(publicKey)) {
                        throw new NullPointerException("Please configure rsa.encrypt.publicKey parameter!");
                    }

                    byte[] data = content.getBytes();
                    byte[] encodedData = RSAUtil.encrypt(data, publicKey);
                    String result = Base64Util.encode(encodedData);
                    if (this.encryptConfig.isShowLog()) {
                        log.info("Pre-encrypted data：{}，After encryption：{}", content, result);
                    }

                    return result;
                } catch (Exception var13) {
                    log.error("Encrypted data exception", var13);
                }
            }

        }
        return body;
    }
}
