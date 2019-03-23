package com.graduate.jrsmain.util;

public class LawException extends RuntimeException{

    private ResultCode code;

    public LawException(ResultCode code) {
        super(code.getCode()+" "+ code.getMessage());
        this.code = code;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }
}
