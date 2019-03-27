package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Process;
import com.graduate.jrsmain.service.ProcessService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Override
    public List<Process> simpleSearch(String message, Pageable pageable) {
        List<Process> processes = new ArrayList<>();
        return processes;
    }
}
