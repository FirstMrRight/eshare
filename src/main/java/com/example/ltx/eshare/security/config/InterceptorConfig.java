package com.example.ltx.eshare.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: LiuTX
 * @date: 2021/7/16 10:17
 * @description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public SignatureInterceptor getSignatureInterceptor() {
        return new SignatureInterceptor();
    }

    /**
     * 注册拦截器
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSignatureInterceptor())
                .addPathPatterns("/**");
    }
}
