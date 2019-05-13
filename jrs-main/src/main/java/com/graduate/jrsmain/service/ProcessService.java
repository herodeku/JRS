package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Process;
import com.graduate.jrsmain.vo.AdvProcess;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface ProcessService {
    Process findOne(String id,String username,boolean b);
    List<Process> findAll(Pageable pageable);
    List<Process> search(String message, Pageable pageable);
    List<Process> advSearch(AdvProcess advProcess, Pageable pageable);
    Integer searchNum(String message);
    Integer advSearchNum(AdvProcess advProcess);
    Map<String,Map<Object,Long>>  aggregationCount();
}
