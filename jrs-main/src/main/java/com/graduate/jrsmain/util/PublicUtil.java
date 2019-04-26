package com.graduate.jrsmain.util;

import com.graduate.jrsmain.bean.History;
import com.graduate.jrsmain.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PublicUtil {

    @Autowired
    private HistoryService historyServiceImpl;

    public void storeHistory(Object obj,String id,String username,Class clazz){
        if(obj==null){
            throw new LawException(ResultCode.INDEX_NOT_FOUND);
        }
         historyServiceImpl.addHistory(new History(id,username,clazz.getSimpleName(),new Date().getTime()));
    }
}
