package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.springframework.data.domain.Pageable;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface JudgmentService {
    Judgment findOne(String id,String username,boolean b);
    List<Judgment> findAll(Pageable pageable);
    List<Judgment> search(String message, Pageable pageable);
    List<Judgment> advSearch(AdvJudgment advJudgment, Pageable pageable);
    Integer searchNum(String message);
    Integer advSearchNum(AdvJudgment advJudgment);
}
