package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Execution;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ExecutionService {
    Execution findOne(String id);
    List<Execution> search(String message, Pageable pageable);
    Integer searchNum(String message);
}
