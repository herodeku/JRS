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
    private ElasticsearchTemplate elasticsearchTemplate;

    private  QueryBuilder queryBuilder;

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
        queryBuilder=null;
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
    public Long searchNum() {
        return EsUtil.aggregationCount(elasticsearchTemplate,EsUtil.aggregation(queryBuilder,"","judgment","message","count"));
    }

    @Override
    public Map<String,Map<Object, Long>>  aggregationCount() {
        return publicUtil.aggregationTermsClassify(queryBuilder, elasticsearchTemplate,"judgment","message", "year","caseType","courtName");
    }
}
