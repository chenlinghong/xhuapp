package com.app.enums;

/**
 * Created with IntelliJ IDEA
 * Created By Mr.Chen
 * Date: 2018/5/13
 * Time: 9:11
 */
public enum ResultEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "请求成功"),
    PARAM_ERROR(1, "参数错误"),
    PARAM_IS_NULL(2, "参数为空"),
    INSUFFICIENT_PERMISSIONS(3, "权限不足"),

    NO_USER(100, "用户不存在"),
    PASSWORD_ERROR(101, "密码错误"),
    USER_IS_NOT_LOGGED_IN(102, "用户未登录"),
    NEW_PASSWORD_IS_TOO_SHORT(110, "密码不能少于6个字符"),
    NEW_PASSWORD_IS_TOO_LONG(111, "密码不能多于30个字符"),
    REAL_NAME_TOO_LONG(112, "真实姓名不能超过8个字符"),
    ORGANIZATION_TOO_LONG(113, "组织单位名称过长"),
    MOTTO_TOO_LONG(114, "用户签名过长"),
    WECHAT_TOO_LONG(115, "WeChat名称过长"),
    WEIBO_TOO_LONG(116, "微博名称过长"),
    QQ_TOO_LONG(117, "QQ名称过长"),
    GITHUB_TOO_LONG(118, "GitHub名称过长"),
    ZHIHU_TOO_LONG(119, "知乎名称过长"),
    PERSON_SITE_TOO_LONG(130, "个人长点名称过长"),
    EMAIL_TOO_LONG(131, "邮箱名称过长"),
    INTRODUCE_TOO_LONG(132, "个人介绍过长"),

    MAJOR_NOT_EXIST(120, "未录入此专业"),
    TELEPHONE_IS_ILLEGAL(121, "电话号码无效"),
    MESSAGE_NOT_EXIST(122, "信息不存在"),

    STRING2DATE_ERROR(140, "时间转换错误"),

    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
