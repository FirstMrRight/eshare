package com.example.ltx.eshare;

import com.example.ltx.eshare.common.annotation.EnableSecurity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Liu-PC
 */
@MapperScan(basePackages = "com.example.ltx.eshare.module.mapper")
@EnableSecurity
@SpringBootApplication
public class EshareApplication {
    public static void main(String[] args) {
        SpringApplication.run(EshareApplication.class, args);
    }

}
