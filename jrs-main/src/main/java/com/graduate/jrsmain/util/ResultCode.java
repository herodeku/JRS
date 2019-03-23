package com.graduate.jrsmain.util;

public enum ResultCode {
    SCUUESS("0000","成功"),
    LOGIN_ERROR("0001","登陆异常,未找到相应用户"),
    REGISTER_ERROR("0002","注册失败,数据插入异常"),
    DUPLICATEKEY_ERROR("0003","用户名重复"),
    SYSERROR("1111","未知异常");
    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
