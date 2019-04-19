package com.graduate.jrsmain.util;

public class ResultUtil {

    private String code;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultUtil error(ResultCode code){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(code.getCode());
        resultUtil.setMessage(code.getMessage());
        resultUtil.setData("无");
        return  resultUtil;
    }
    public static ResultUtil success(Object object){
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(ResultCode.SCUUESS.getCode());
        resultUtil.setMessage(ResultCode.SCUUESS.getMessage());
        resultUtil.setData(object);
        return resultUtil;
    }
}
