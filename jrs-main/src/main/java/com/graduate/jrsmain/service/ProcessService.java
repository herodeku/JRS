package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Process;
import com.graduate.jrsmain.vo.AdvProcess;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProcessService {
    Process findOne(String id,String username,boolean b);
    List<Process> findAll(Pageable pageable);
    List<Process> search(String message, Pageable pageable);
    List<Process> advSearch(AdvProcess advProcess, Pageable pageable) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;
}
