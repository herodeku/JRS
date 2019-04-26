package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Process;
import com.graduate.jrsmain.vo.AdvJudgment;
import com.graduate.jrsmain.vo.AdvProcess;
import org.springframework.data.domain.Pageable;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ProcessService {
    Process findOne(String id,String username,boolean b);
    List<Process> search(String message, Pageable pageable);
    List<Process> advSearch(AdvProcess advProcess, Pageable pageable);
    Integer searchNum(String message);
    Integer advSearchNum(AdvProcess advProcess);
}
