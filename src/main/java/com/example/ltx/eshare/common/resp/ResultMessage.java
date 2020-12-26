package com.example.ltx.eshare.common.resp;
import com.example.ltx.eshare.common.enums.ResultCode;
import com.example.ltx.eshare.common.exception.IResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 结果消息
 * @author Liu-PC
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage implements Serializable {
    private Integer code;
    private String msg;
    private Object data;
    private String sign;
    private Date timeStamp;

    public boolean hasError() {
        return !this.code.equals(ResultCode.SUCCESS.getCode());
    }

    public static ResultMessage success() {
        ResultMessage result = new ResultMessage();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static ResultMessage success(Object data) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static ResultMessage success(Object data, String sign) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setResultCode(ResultCode.SUCCESS);
        resultMessage.setData(data);
        resultMessage.setSign(sign);
        resultMessage.setTimeStamp(new Date());
        return resultMessage;
    }


    public static ResultMessage failure(ResultCode resultCode) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        result.setTimeStamp(new Date());
        return result;
    }

    public static ResultMessage failure(ResultCode resultCode, Object data) {
        ResultMessage result = new ResultMessage();
        result.setResultCode(resultCode);
        result.setData(data);
        result.setTimeStamp(new Date());
        return result;
    }

    public static ResultMessage failure(IResponseEnum responseEnum, Object data) {
        ResultMessage result = new ResultMessage();
        result.setCode(responseEnum.getCode());
        result.setMsg(responseEnum.getMessage());
        result.setData(data);
        result.setTimeStamp(new Date());
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
