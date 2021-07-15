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

            StringBuilder signSb = new StringBuilder();
            if ("POST".equalsIgnoreCase(req.getMethod())) {
                InputStream is = req.getInputStream();  // 获取请求体的流

                if (is != null) {
                    // 转成字节数组
                    byte[] bodyBytes = IoUtil.readBytes(is);
                    is.close(); // 关闭流
                    String bodyStr = new String(bodyBytes);  // 转成字符串
                    JSONObject bodyJObject = JSONObject.parseObject(bodyStr); // 将字符串转成json对象
                    for (String paraName : bodyJObject.keySet()) {
                        signSb.append(paraName).append("=").append(bodyJObject.get(paraName)).append("&");
                    }
                }
            }
            String signNew = signSb.substring(0, signSb.length() - 1); // 获取拼接后的签名


            // 处理业务异常
            BusinessException businessException = (BusinessException) e;
            log.warn(String.format("访问 %s -> %s 出现业务异常！", req.getRequestURI(), method.toString()), e);
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
