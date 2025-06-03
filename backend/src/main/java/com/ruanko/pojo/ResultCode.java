package com.ruanko.pojo;

public enum ResultCode {
    SUCCESS(200, "成功"),
    FAILED(400, "失败"),
    USER_LOGIN_ERROR(2001, "失败"),
    USER_HAS_EXISTED(2002, "失败");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}