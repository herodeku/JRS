package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Judgment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JudgmentService {
    List<Judgment> simpleSearch(String message, Pageable pageable);
    List<Judgment> getAllJudgment();
    void deleteAllJudgment();
    void saveCivil();
}
