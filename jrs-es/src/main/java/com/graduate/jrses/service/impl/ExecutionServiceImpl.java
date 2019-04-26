package com.graduate.jrses.service.impl;

import com.graduate.jrses.bean.Execution;
import com.graduate.jrses.bean.Judgment;
import com.graduate.jrses.repository.ExecutionRepository;
import com.graduate.jrses.service.ExecutionService;
import com.graduate.jrses.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    @Override
    public String storeIndex(Execution execution) {
        if(StringUtils.isNotBlank(execution.getId())){
            executionRepository.index(execution);
            return ResultUtil.SUCCESS;
        }else {
            return ResultUtil.ERROR;
        }
    }
}
