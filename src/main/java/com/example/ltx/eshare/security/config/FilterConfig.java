package com.example.ltx.eshare.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author: LiuTX
 * @date: 2021/7/16 10:16
 * @description:
 */
@Configuration
public class FilterConfig {
    /**
     * 注册过滤器
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(replaceStreamFilter());
        registration.addUrlPatterns("/*");
        registration.setName("streamFilter");
        return registration;
    }

    /**
     * 实例化StreamFilter
     *
     * @return Filter
     */
    @Bean(name = "replaceStreamFilter")
    public Filter replaceStreamFilter() {
        return new ReplaceStreamFilter();
    }
}
