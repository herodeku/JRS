package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Execution;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ExecutionService {
    Execution findOne(String id,String username,boolean b);
    List<Execution> findAll(Pageable pageable);
    List<Execution> search(String message, Pageable pageable);
    Long searchNum();
}
