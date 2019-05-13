package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.repository.JudgmentRepository;
import com.graduate.jrsmain.service.JudgmentService;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.util.PublicUtil;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JudgmentServiceImpl implements JudgmentService {

    @Autowired
    private JudgmentRepository judgmentRepository;

    @Autowired
    private PublicUtil publicUtil;

    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;

    public  QueryBuilder queryBuilder;

    @Override
    public Judgment findOne(String id,String username,boolean b) {
        Judgment one = judgmentRepository.findOne(id);
        if(b==true){
            publicUtil.storeHistory(one, id,username , this.getClass());
        }
        return one;
    }

    @Override
    public List<Judgment> findAll(Pageable pageable) {
        return EsUtil.search(judgmentRepository, null,pageable);
    }

    @Override
    public List<Judgment> search(String message, Pageable pageable) {
        queryBuilder = EsUtil.createJudgmentBoolQueryBuilder(message);
        return EsUtil.search(judgmentRepository,queryBuilder,pageable);
    }

    @Override
    public List<Judgment> advSearch(AdvJudgment advJudgment, Pageable pageable){
        queryBuilder = EsUtil.createAdvJudgmentBoolQueryBuilder(advJudgment);
        return EsUtil.search(judgmentRepository,queryBuilder,pageable);
    }

    @Override
    public Integer searchNum(String message) {
        return EsUtil.searchNum(judgmentRepository,queryBuilder);
    }

    @Override
    public Integer advSearchNum(AdvJudgment advJudgment){
        return EsUtil.searchNum(judgmentRepository,queryBuilder);
    }

    @Override
    public Map<String,Map<Object, Long>>  aggregationCount() {
        return publicUtil.aggregationCount(queryBuilder, elasticsearchTemplate,"judgment","message", "year","caseType","courtName");
    }
}
