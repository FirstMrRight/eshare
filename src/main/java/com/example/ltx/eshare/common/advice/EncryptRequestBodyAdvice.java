package com.example.ltx.eshare.common.advice;

import com.example.ltx.eshare.common.annotation.Decrypt;
import com.example.ltx.eshare.common.annotation.Encrypt;
import com.example.ltx.eshare.common.model.DecryptHttpInputMessage;
import com.example.ltx.eshare.common.model.EncryptConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {
    private boolean encrypt;
    @Autowired
    private EncryptConfig encryptConfig;

    public EncryptRequestBodyAdvice() {
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Annotation[] annotations = methodParameter.getDeclaringClass().getAnnotations();
        if (annotations.length > 0 && this.encryptConfig.isOpen()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Encrypt) {
                    return this.encrypt = true;
                }
            }
        }

        return this.encrypt = Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(Decrypt.class) && this.encryptConfig.isOpen();
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (this.encrypt) {
            try {
                return new DecryptHttpInputMessage(inputMessage, this.encryptConfig.getPrivateKey(), this.encryptConfig.getCharset(), this.encryptConfig.isShowLog());
            } catch (Exception var6) {
                log.error("Decryption failed", var6);
            }
        }

        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}