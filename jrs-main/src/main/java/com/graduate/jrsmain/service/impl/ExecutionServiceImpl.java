package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Execution;
import com.graduate.jrsmain.repository.ExecutionRepository;
import com.graduate.jrsmain.service.ExecutionService;
import com.graduate.jrsmain.util.*;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    @Autowired
    private PublicUtil publicUtil;

    private QueryBuilder queryBuilder;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Execution findOne(String id,String username,boolean b) {
        Execution one = executionRepository.findOne(id);
        if(b==true){
            publicUtil.storeHistory(one, id,username , this.getClass());
        }
        return one;
    }

    @Override
    public List<Execution> findAll(Pageable pageable) {
        queryBuilder = null;
        return EsUtil.search(executionRepository, null, pageable);
    }

    @Override
    public List<Execution> search(String message, Pageable pageable) {
        queryBuilder = EsUtil.createExecutionBoolQueryBuilder(message);
        return EsUtil.search(executionRepository,queryBuilder,pageable);
    }

    @Override
    public Long searchNum() {
        return EsUtil.aggregationCount(elasticsearchTemplate,EsUtil.aggregation(queryBuilder,"","execution","message","count"));
    }
}
