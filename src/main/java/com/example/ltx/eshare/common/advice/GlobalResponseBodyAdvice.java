package com.example.ltx.eshare.common.advice;

import com.example.ltx.eshare.common.enums.ResultCode;
import com.example.ltx.eshare.common.exception.BaseException;
import com.example.ltx.eshare.common.exception.BisException;
import com.example.ltx.eshare.common.exception.BusinessException;
import com.example.ltx.eshare.common.resp.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
        ResultMessage result;

//        boolean hasEncryptBody = methodParameter.hasMethodAnnotation(EncryptBody.class);

        try {
//            if (hasEncryptBody) {
//                String data = JSON.toJSONString(object);
//                String encryptStr = RSAUtil.encrypt(data, publicKey);
//                String signStr = data + "&secret=" + secret;
//                String sign = MD5Util.MD5Encode(signStr, "utf-8");
//                result = ResultMessage.success(encryptStr, sign);
//            } else {
                result = ResultMessage.success(object);
//            }
        } catch (Exception e) {
            log.error("rsa 解密失败:", e);
            return ResultMessage.failure(ResultCode.SYSTEM_INNER_ERROR);
        }

        return result;
    }

    @ExceptionHandler({BisException.class})
    public ResultMessage handleBusiness(BaseException e) {
        BisException bisException = (BisException) e;
        return ResultMessage.failure(bisException.getResponseEnum(), bisException.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResultMessage exceptions(Exception e) {
        if (e instanceof BusinessException) {
            // 处理业务异常
            BusinessException businessException = (BusinessException) e;
            return ResultMessage.failure(businessException.getResultCode());
        } else if (e instanceof MethodArgumentNotValidException) {
            // 处理javax.validation异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            String msg = Objects.requireNonNull(methodArgumentNotValidException.getBindingResult().getFieldError()).getDefaultMessage();
            return ResultMessage.failure(ResultCode.PARAM_IS_INVALID, msg);
        } else if (e instanceof BisException) {
            BusinessException businessException = (BusinessException) e;
            return ResultMessage.failure(businessException.getResultCode());
        } else if (e instanceof RuntimeException) {
            BusinessException businessException = (BusinessException) e;
            return ResultMessage.failure(businessException.getResultCode());
        }
        log.error("exception is {}", e.getMessage(), e);
        return ResultMessage.failure(ResultCode.SYSTEM_INNER_ERROR);
    }

}
