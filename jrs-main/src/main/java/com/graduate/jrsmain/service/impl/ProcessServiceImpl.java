package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Process;
import com.graduate.jrsmain.repository.ProcessRepository;
import com.graduate.jrsmain.service.ProcessService;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.util.PublicUtil;
import com.graduate.jrsmain.vo.AdvProcess;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Autowired
    private PublicUtil publicUtil;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private QueryBuilder queryBuilder;

    @Override
    public Process findOne(String id,String username,boolean b) {
        Process one = processRepository.findOne(id);
        if(b==true){
            publicUtil.storeHistory(one, id,username , this.getClass());
        }
        return one;
    }

    @Override
    public List<Process> findAll(Pageable pageable) {
        return EsUtil.search(processRepository,null,pageable);
    }

    @Override
    public List<Process>  search(String message, Pageable pageable) {
        queryBuilder = EsUtil.createProcessBoolQueryBuilder(message);
        return EsUtil.search(processRepository,queryBuilder,pageable);
    }

    @Override
    public List<Process> advSearch(AdvProcess advProcess, Pageable pageable){
        queryBuilder = EsUtil.createAdvProcessBoolQueryBuilder(advProcess);
        return EsUtil.search(processRepository,queryBuilder,pageable);
    }

    @Override
    public Integer searchNum(String message) {
        return EsUtil.searchNum(processRepository,queryBuilder);
    }

    @Override
    public Integer advSearchNum(AdvProcess advProcess) {
        return EsUtil.searchNum(processRepository,queryBuilder);
    }

    @Override
    public Map<String,Map<Object, Long>> aggregationCount() {
        return publicUtil.aggregationCount(queryBuilder, elasticsearchTemplate, "process","message","year","programme","province");
    }
}
