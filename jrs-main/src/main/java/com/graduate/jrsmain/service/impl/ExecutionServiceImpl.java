package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Execution;
import com.graduate.jrsmain.repository.ExecutionRepository;
import com.graduate.jrsmain.service.ExecutionService;
import com.graduate.jrsmain.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    @Autowired
    private PublicUtil publicUtil;

    @Override
    public Execution findOne(String id,String username,boolean b) {
        Execution one = executionRepository.findOne(id);
        if(b==true){
            publicUtil.storeHistory(one, id,username , this.getClass());
        }
        return one;
    }

    @Override
    public List<Execution> search(String message, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"title", "content"});
        return EsUtil.search(executionRepository,boolQueryBuilder,pageable);
    }
}
