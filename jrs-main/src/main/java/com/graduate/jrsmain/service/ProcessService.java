package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Process;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProcessService {
    List<Process> simpleSearch(String message, Pageable pageable);
}
