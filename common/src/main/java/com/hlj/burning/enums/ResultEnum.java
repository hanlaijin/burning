package com.hlj.burning.enums;

public enum ResultEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    SYSTEM_ERROR(500, "系统错误"),
    PARAM_ERROR(-1000, "请求参数不合法"),
    LOGIN_ERROR(-1001, "用户名或密码错误");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}