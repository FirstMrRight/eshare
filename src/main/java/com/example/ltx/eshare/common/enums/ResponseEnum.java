package com.example.ltx.eshare.common.enums;


import com.example.ltx.eshare.common.exception.BisExceptionAssert;

public enum ResponseEnum implements BisExceptionAssert {
    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),
    HEADER_LANG_NOT_FOUND(10005, "Header中的lang参数没有传递"),
    PARAM_LANG_NOT_SUPPORT(10006, "语言环境不支持"),
    PARAM_APP_ID_NOT_SUPPORT(10007, "APP_ID不正确"),
    PARAM_PROPERTY_TYPE_NOT_SUPPORT(10008, "产品属性不正确"),


    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_NOT_EXIST_OR_ERROR(20002, "用户不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_SMS_SENTED(20004, "验证码已发送,重复发送"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    USER_SMS_ERROR(20006, "验证码不正确"),
    USER_SMS_EXPIRE(20007, "验证码已失效"),
    USER_PASSWORD_NOT_SAME(20008, "两次输入的密码不一致"),
    USER_ALTER_PASSWORD_ERROR(20009, "密码修改失败"),
    USER_ROLE_PERMISSION_ERROR(20010, "用户不具备该角色/权限"),
    ROLE_DOES_NOT_EXIST(20011, "该角色不存在"),
    WRONG_PHONE_NUMBER(20012, "手机号错误"),
    SMS_IS_EXIST(20013, "验证码已发送，请勿重复点击"),
    LOGIN_FILURE(20014, "登录失败"),
    USER_ACCOUNT_LOCKED(20014, "账户已被锁定"),

    CLIENT_USER_NOT_LOGGED_IN(25001, "客户端用户未登录"),
    CLIENT_USER_UPDATE_FAILED(25002, "客户端用户更新失败"),
    CLIENT_USER_UPDATE_PARAM_NULL(25003, "客户端用户更新失败，参数为空"),
    CLIENT_USER_QUICKPASS_FAILED(25004, "一键登录失败"),
    CLIENT_USER_QUICKPASS_PARAM_NULL(25005, "一键登录失败，参数为空"),
    CLIENT_USER_SAVE_PARAM_NULL(25051, "客户端用户保存失败，参数为空"),
    CLIENT_USER_SMSLOGIN_PARAM_NULL(25051, "短信登录失败，参数为空"),
    CLIENT_USER_SMSLOGIN_VCODE_FAILED(25052, "短信登录失败，验证码失效"),
    CLIENT_USER_SMSLOGIN_FAILED(25053, "短信登录失败，无效的短信验证码"),
    CLIENT_USER_SENDSMS_PARAM_NULL(25054, "短信发送失败，参数为空"),
    CLIENT_USER_SENDSMS_FAILED(25055, "短信发送失败"),
    CLIENT_USER_MANAGE_FIND_PARAM_NULL(25101, "客户端用户查询失败，参数为空"),
    CLIENT_USER_MANAGE_FIND_FAILED(25102, "客户端用户查询失败"),
    CLIENT_USER_REALNAME_AUTH_PARAM_NULL(25103, "实名认证失败，参数为空"),
    CLIENT_USER_REALNAME_AUTH_FAILED(25104, "实名认证失败"),
    CLIENT_USER_REALNAME_AUTH_CONFIRM_PARAM_NULL(25105, "实名认证确认失败，参数为空"),
    CLIENT_USER_REALNAME_AUTH_CONFIRM_FAILED(25106, "实名认证确认失败"),
    CLIENT_USER_REALNAME_AUTH_EXISTS_FAILED(25107, "实名认证失败，已实名认证"),
    CLIENT_USER_LABEL_FIND_PARAM_NULL(25151, "客户端用户标签查询失败，参数为空"),
    CLIENT_USER_LABEL_FINDBYID_PARAM_NULL(25152, "客户端用户标签详情查询失败，参数为空"),
    CLIENT_USER_LABEL_SAVE_PARAM_NULL(25153, "客户端用户标签保存失败，参数为空"),
    CLIENT_USER_LABEL_UPDATE_PARAM_NULL(25154, "客户端用户标签修改失败，参数为空"),
    CLIENT_USER_LABEL_DELETE_PARAM_NULL(25155, "客户端用户标签删除失败，参数为空"),
    CLIENT_USER_DEPT_SAVE_PARAM_NULL(25156, "部门保存失败，参数为空"),
    CLIENT_USER_DEPT_UPDATE_PARAM_NULL(25157, "部门修改失败，参数为空"),
    CLIENT_USER_DEPT_DELETE_PARAM_NULL(25158, "部门删除失败，参数为空"),
    CLIENT_USER_DEPT_FINDUSER_PARAM_NULL(25159, "部门人员查询失败，参数为空"),
    CLIENT_USER_DEPT_SET_USER_PARAM_NULL(25160, "部门设置人员失败，参数为空"),
    CLIENT_USER_DEPT_DEL_USER_PARAM_NULL(25161, "部门删除人员失败，参数为空"),
    CLIENT_USER_SAVE_FAILED(25162, "用户保存失败"),
    CLIENT_USER_DELETE_FAILED(25162, "用户删除失败"),
    CLIENT_FILE_UPLOAD_FAILED(25999, "文件上传失败"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),
    CATEGORY_PARENT_NOT_FOUND(30002, "父级分类没有找到"),
    CATEGORY_DISABLE(30003, "父级分类不可用"),
    CATEGORY_NOT_FOUND(30004, "分类没有找到"),
    PROPERTY_NOT_FOUND(30005, "属性没有找到"),
    CATEGORY_PROPERTY_EXISTS(30006, "分类属性已经存在"),
    OPERATION_IS_BUSY(30007, "用户操作过于频繁"),
    TIERED_PRICING_NOT_CONFIG(30008, "阶梯价格未配置"),
    GROUP_NOT_FOUND(30009, "团购信息没有找到"),
    GROUP_NOT_HIGH(30010, "未满足最低起团金额"),
    GROUP_NOT_HIGH_COUNT(30011, "未满足最低起团数量"),
    GROUP_END(30012, "团购数量已达到，不能参团"),
    USER_TYPE_ERR(30013, "用户类型不匹配"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-59999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),
    DATA_HAS_BEEN_DELETED(50004, "数据已被删除"),
    PRODUCT_HAS_BEEN_DELETED(50005, "商品已下架"),
    PHONE_ALREADY_EXISTS(50006, "手机号已经存在"),
    RESULE_DATA_USER_NONE(50007, "缺少用户补充信息"),
    RESULE_WECHAT_PHONE_NONE(50008, "微信手机号获取失败"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_FAIL(60003, "外部系统接口调用失败"),
    INTERFACE_FORBID_VISIT(60004, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60005, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60006, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60007, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限"),
    PERMISSION_VERIFY_SIGN_ERROR(70002, "验证签名失败"),
    PERMISSION_NOT_SAFE(70003, "不安全的访问"),
    PERMISSION_NOT_ENCRYPT(7004, "访问未加密"),

    /* 正则格式错误：80001-89999 */
    REGEXP_PHONE_ERROR(80001, "手机格式错误"),
    REGEXP_PASSWORD_ERROR(80002, "密码格式错误"),
    REGEXP_VAL_PASSWORD_ERROR(80003, "两次密码不一致"),
    REGEXP_EMAIL_ERROR(80004, "邮箱格式错误"),

    /* 注册信息错误：90001-99999 */
    PERSONAL_NAME_NULL(90001, "用户名为空"),
    PERSONAL_NAME_EXIST(90001, "用户名存在"),
    PERSONAL_ID_CARD_NULL(90002, "身份证号码不正确"),
    PERSONAL_ID_CARD_FRONT_NULL(90003, "身份证正面为空"),
    PERSONAL_ID_CARD_OBVERSE_NULL(90004, "身份证反面为空"),

    ENTERPRISE_NAME_NULL(80005, "公司名称为空"),
    ENTERPRISE_NAME_EXIST(80005, "公司名称已存在"),
    ENTERPRISE_BUSINESS_CREDIT_CODE_ERROR(90006, "统一社会信用代码错误"),
    ENTERPRISE_BUSINESS_LICENSE(90007, "营业执照照片为空"),

    /* io错误：100001-199999 */
    IO_Excel_INPUT_ERROR(100001, "Excel导入失败"),

    /* 其他 * */
    OTHER_ERROR_NAME(500, "收货人错误"),
    OTHER_ERROR_DETAIL_ADDRESS(500, "详细地址错误");

    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

}
