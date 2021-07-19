package com.example.ltx.eshare.common.advice;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.ltx.eshare.common.enums.ResponseEnum;
import com.example.ltx.eshare.common.enums.ResultCode;
import com.example.ltx.eshare.common.exception.BaseException;
import com.example.ltx.eshare.common.exception.BisException;
import com.example.ltx.eshare.common.exception.BusinessException;
import com.example.ltx.eshare.common.resp.ResultMessage;
import com.google.common.io.ByteSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;


/**
 * @author Liu-PC
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice {


    public static final String POST = "POST";


    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (object instanceof ResultMessage) {

            return object;
        }


        return object;
    }

    @ExceptionHandler({BisException.class})
    public ResultMessage handleBusiness(BaseException e) {
        BisException bisException = (BisException) e;
        return ResultMessage.failure(bisException.getResponseEnum(), bisException.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResultMessage exceptions(HttpServletRequest req, HandlerMethod method, Exception e) throws IOException {
        if (e instanceof BusinessException) {
            // 处理业务异常
            BusinessException businessException = (BusinessException) e;
            return ResultMessage.failure(businessException.getResultCode());
        } else if (e instanceof MethodArgumentNotValidException) {
            // 处理javax.validation异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            String msg = Objects.requireNonNull(methodArgumentNotValidException.getBindingResult().getFieldError()).getDefaultMessage();
            return ResultMessage.failure(ResultCode.PARAM_IS_INVALID, msg);
        } else if (e instanceof NullPointerException) {
            log.error(String.format("访问 %s -> %s 出现系统异常！", req.getRequestURI(), method.toString()), e);
            return ResultMessage.failure(ResultCode.PARAM_IS_INVALID, ResponseEnum.SYSTEM_INNER_ERROR.getMessage());
        }
        log.error("exception is {}", e.getMessage(), e);
        return ResultMessage.failure(ResultCode.SYSTEM_INNER_ERROR);
    }

}
