package com.graduate.jrsmain.util;

public enum ResultCode {
    SCUUESS("0000","成功"),
    LOGIN_ERROR("0001","登陆异常,未找到相应用户"),
    DUPLICATEKEY_ERROR("0002","用户名重复"),
    DUPLICATEPHONE_ERROR("0003","手机号重复"),
    REGISTER_ERROR("0004","注册失败"),
    INDEX_NOT_FOUND("0005","索引不存在"),
    MESSAGE_NOT_READABLE("0006","消息格式错误"),
    HISTORY_NOT_STORE("0007","历史纪录没有被记录"),
    HISTORY_NOT_DELETE("0008","历史纪录删除错误"),
    SYSERROR("1111","未知异常");
    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultCode(String code, Exception e) {
        this.code = code;
        this.message = e.getCause().getMessage();
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
