package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import com.graduate.jrsmain.util.ResultUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultUtil handle(Exception e){
        if(e instanceof LawException){
            LawException lawException = (LawException) e;
            return ResultUtil.error(lawException.getCode());
        }else if(e instanceof DuplicateKeyException){
            if(e.getCause().getMessage().indexOf("PRIMARY")>-1){
                return ResultUtil.error(ResultCode.DUPLICATEKEY_ERROR);
            }else {
                return ResultUtil.error(ResultCode.DUPLICATEPHONE_ERROR);
            }
        }else if(e instanceof HttpMessageNotReadableException){
            return ResultUtil.error(ResultCode.MESSAGE_NOT_READABLE);
        }
        else{
            e.printStackTrace();
            return ResultUtil.error(ResultCode.SYSERROR);
        }
    }
}
