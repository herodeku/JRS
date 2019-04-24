package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.repository.JudgmentRepository;
import com.graduate.jrsmain.service.JudgmentService;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import com.graduate.jrsmain.vo.AdvJudgment;
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

    private static Integer messageNum;

    @Override
    public Judgment findOne(String id) {
        Judgment one = judgmentRepository.findOne(id);
        if(one==null){
            throw new LawException(ResultCode.INDEX_NOT_FOUND);
        }
        return one;
    }

    @Override
    public List<Judgment> search(String message, Pageable pageable) {
        return EsUtil.search(judgmentRepository,EsUtil.createJudgmentBoolQueryBuilder(message),pageable);
    }

    @Override
    public List<Judgment> advSearch(AdvJudgment advJudgment, Pageable pageable){
        return EsUtil.search(judgmentRepository,EsUtil.createAdvJudgmentBoolQueryBuilder(advJudgment),pageable);
    }

    @Override
    public Integer searchNum(String message) {
        return EsUtil.searchNum(judgmentRepository,EsUtil.createJudgmentBoolQueryBuilder(message));
    }

    @Override
    public Integer advSearchNum(AdvJudgment advJudgment){
        return EsUtil.searchNum(judgmentRepository,EsUtil.createAdvJudgmentBoolQueryBuilder(advJudgment));
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
