package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.repository.JudgmentRepository;
import com.graduate.jrsmain.service.JudgmentService;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.util.PublicUtil;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JudgmentServiceImpl implements JudgmentService {

    @Autowired
    private JudgmentRepository judgmentRepository;

    @Autowired
    private PublicUtil publicUtil;

    @Override
    public Judgment findOne(String id,String username,boolean b) {
        Judgment one = judgmentRepository.findOne(id);
        if(b==true){
            publicUtil.storeHistory(one, id,username , this.getClass());
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
