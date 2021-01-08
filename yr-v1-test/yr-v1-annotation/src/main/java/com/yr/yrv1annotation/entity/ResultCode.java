package com.yr.yrv1annotation.entity;

public enum ResultCode {

    /*成功状态码*/
    SUCCESS(1, "成功"),
    PARAM_IS_BLANK(1002, "参数为空");

    private Integer code;
    private String message;

    ResultCode (Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
