package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import com.graduate.jrsmain.util.ResultUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

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
        }
        else{
            return ResultUtil.error(ResultCode.SYSERROR);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
}
