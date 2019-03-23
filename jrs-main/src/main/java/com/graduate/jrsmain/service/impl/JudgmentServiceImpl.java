package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.repository.JudgmentRepository;
import com.graduate.jrsmain.service.JudgmentService;
import com.graduate.jrsmain.util.LoadSource;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JudgmentServiceImpl implements JudgmentService {

    @Autowired
    private JudgmentRepository judgmentRepository;


    @Override
    public List<Judgment> simpleSearch(String message, Pageable pageable) {
        boolean convertStatus = true;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-DD-mm");
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(message);
        }catch (ParseException e){
            convertStatus = false;
        }
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(convertStatus==true){
            boolQueryBuilder.should(QueryBuilders.matchQuery("judgeDate",message));
        }
        boolQueryBuilder.should(QueryBuilders.matchQuery("judgeProcess",message));
        boolQueryBuilder.should(QueryBuilders.matchQuery("caseNum",message));
        boolQueryBuilder.should(QueryBuilders.matchQuery("caseName",message));
        boolQueryBuilder.should(QueryBuilders.matchQuery("courtName",message));
        boolQueryBuilder.should(QueryBuilders.matchQuery("judgeContent",message));
        Iterable<Judgment> judgments = judgmentRepository.search(boolQueryBuilder,pageable);
        Iterator<Judgment> iterator = judgments.iterator();
        List<Judgment> judgments1 = new ArrayList<>();
        while (iterator.hasNext()){
            judgments1.add(iterator.next());
        }
        return judgments1;
    }

    @Override
    public List<Judgment> getAllJudgment() {
        List<Judgment> cases = new ArrayList<>();
        Iterator<Judgment> iterator = judgmentRepository.findAll().iterator();
        while (iterator.hasNext()){
            cases.add(iterator.next());
        }
        return cases;
    }

    @Override
    public void deleteAllJudgment() {
        judgmentRepository.deleteAll();
    }

    @Override
    public void saveCivil() {
        List<Judgment> judementCivilCases = LoadSource.loadToCivil("C:\\Users\\hasee\\Desktop\\civil.txt");
        for (Judgment judgment:judementCivilCases) {
            judgmentRepository.save(judgment);
        }
    }
}
