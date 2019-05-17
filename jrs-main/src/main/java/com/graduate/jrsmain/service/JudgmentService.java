package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface JudgmentService {
    Judgment findOne(String id,String username,boolean b);
    List<Judgment> findAll(Pageable pageable);
    List<Judgment> search(String message, Pageable pageable);
    List<Judgment> advSearch(AdvJudgment advJudgment, Pageable pageable);
    Long searchNum();
    Map<String,Map<Object,Long>>  aggregationCount();
}
