package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import com.graduate.jrsmain.util.ResultUtil;
import org.springframework.dao.DuplicateKeyException;
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
            return ResultUtil.error(ResultCode.DUPLICATEKEY_ERROR);
        }else{
            return ResultUtil.error(ResultCode.SYSERROR);
        }
    }
}
