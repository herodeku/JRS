package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Execution;
import com.graduate.jrsmain.repository.ExecutionRepository;
import com.graduate.jrsmain.service.ExecutionService;
import com.graduate.jrsmain.util.DateUtil;
import com.graduate.jrsmain.util.EsUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    @Override
    public Execution findOne(String id) {
        return executionRepository.findOne(id);
    }

    @Override
    public List<Execution> search(String message, Pageable pageable) {
        return EsUtil.search(executionRepository,EsUtil.createExecutionBoolQueryBuilder(message),pageable);
    }

    @Override
    public Integer searchNum(String message) {
        return EsUtil.searchNum(executionRepository,EsUtil.createExecutionBoolQueryBuilder(message));
    }
}
