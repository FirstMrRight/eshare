package com.example.ltx.eshare.common.annotation;


import com.example.ltx.eshare.common.advice.EncryptRequestBodyAdvice;
import com.example.ltx.eshare.common.advice.EncryptResponseBodyAdvice;
import com.example.ltx.eshare.common.model.EncryptConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({EncryptConfig.class, EncryptResponseBodyAdvice.class, EncryptRequestBodyAdvice.class})
public @interface EnableSecurity {
}
