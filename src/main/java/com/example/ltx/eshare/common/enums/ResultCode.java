package com.example.ltx.eshare.common.enums;

import lombok.Getter;

/**
 *
 */
@Getter
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 参数错误：10001-19999 */
    PARAM_IS_BLANK(10001, "参数为空"),
    PARAM_IS_INVALID(10002, "参数无效"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),
    PARAM_LANG_NOT_SUPPORT(10005, "语言环境不支持"),
    PARAM_APP_ID_NOT_SUPPORT(10006, "APP_ID不正确"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_SMS_ERROR(20006, "验证码不正确"),
    USER_PASSWORD_NOT_SAME(2007, "两次输入的密码不一致"),
    USER_SMS_SENTED(20008, "验证码已发送,重复发送"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),
    CATEGORY_PARENT_NOT_FOUND(30002, "父级分类没有找到"),
    CATEGORY_DISABLE(30003, "父级分类不可用"),
    CATEGORY_NOT_FOUND(30004, "分类没有找到"),
    PROPERTY_NOT_FOUND(30005, "属性没有找到"),

    /* 数据错误：40001-49999 */
    RESULE_DATA_NONE(40001, "数据未找到"),
    DATA_IS_WRONG(40002, "数据有误"),
    DATA_ALREADY_EXISTED(40003, "数据已存在"),
    DATA_HAS_BEEN_DELETED(40004, "数据已被删除"),

    /* 接口错误：50001-59999 */
    INTERFACE_INNER_INVOKE_ERROR(50001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(50002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(50003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(50004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(50005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(50006, "接口负载过高"),

    /* 权限错误：60001-69999 */
    PERMISSION_NO_ACCESS(60001, "无访问权限"),

    /* 正则格式错误：70001-79999 */
    REGEXP_PHONE_ERROR(70001, "手机格式错误"),

    /* 系统错误：90000-99999 */
    SYSTEM_INNER_ERROR(99999, "系统繁忙，请稍后重试"),;

    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
