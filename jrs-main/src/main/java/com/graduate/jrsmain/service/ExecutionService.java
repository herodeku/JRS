package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Execution;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ExecutionService {
    List<Execution> simpleSearch(String message, Pageable pageable);
}
