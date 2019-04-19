package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.repository.JudgmentRepository;
import com.graduate.jrsmain.service.JudgmentService;
import com.graduate.jrsmain.util.DateUtil;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class JudgmentServiceImpl implements JudgmentService {

    @Autowired
    private JudgmentRepository judgmentRepository;

    @Override
    public List<Judgment> search(String message, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(DateUtil.judgeDate(message)==true){
            boolQueryBuilder.should(QueryBuilders.matchQuery("releaseDate",message));
        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"judgeProcess", "caseNum", "caseName","courtName","judgeContent"});
        return EsUtil.search(judgmentRepository,boolQueryBuilder,pageable);
    }

    @Override
    public List<Judgment> advSearch(AdvJudgment advJudgment, Pageable pageable) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotBlank(advJudgment.getJudgeDateGTE())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("judgeDate").gte(advJudgment.getJudgeDateGTE()).lt(advJudgment.getJudgeDateLT()));
        }
        EsUtil.buildAdvBoolQueryBuilder(boolQueryBuilder,EsUtil.getMethod(advJudgment));
        return EsUtil.search(judgmentRepository,boolQueryBuilder,pageable);
    }

    @Override
    public Judgment findOne(String id) {
        Judgment one = judgmentRepository.findOne(id);
        if(one==null){
            throw new LawException(ResultCode.INDEX_NOT_FOUND);
        }
        return one;
    }

//    @Override
//    public List<Judgment> getAllJudgment() {
//        List<Judgment> cases = new ArrayList<>();
//        Iterator<Judgment> iterator = judgmentRepository.findAll().iterator();
//        while (iterator.hasNext()){
//            cases.add(iterator.next());
//        }
//        return cases;
//    }
}
