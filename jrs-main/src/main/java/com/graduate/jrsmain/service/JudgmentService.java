package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.springframework.data.domain.Pageable;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface JudgmentService {
    List<Judgment> search(String message, Pageable pageable);
    List<Judgment> advSearch(AdvJudgment advJudgment, Pageable pageable) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;
    Judgment findOne(String id);
//    void deleteAllJudgment();
//    void saveCivil();
}
