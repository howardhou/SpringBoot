package com.example.mybatisdemo.dto;

public enum ResultStatus {
    SUCCESS(200, "成功"),
    PARAMETER_ERROR(999, "参数错误"),
    PARAMETER_MISS(998, "缺少必须的参数")
    ;

    private int code;
    private String message;

    ResultStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
