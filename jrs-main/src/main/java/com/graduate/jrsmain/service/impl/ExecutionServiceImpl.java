package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Execution;
import com.graduate.jrsmain.service.ExecutionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExecutionServiceImpl implements ExecutionService {
    @Override
    public List<Execution> simpleSearch(String message, Pageable pageable) {
        List<Execution> executions = new ArrayList<>();
        return executions;
    }
}
