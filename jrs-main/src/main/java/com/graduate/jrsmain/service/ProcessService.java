package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.*;
import com.graduate.jrsmain.vo.AdvProcess;
import org.springframework.data.domain.Pageable;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ProcessService {
    List<ProcessGuide> searchGuide(String message, Pageable pageable);
    List<ProcessCase> searchCase(String message, Pageable pageable);
    List<ProcessTrialReport> searchTriaReport(String message, Pageable pageable);
    Object advSearch(AdvProcess advProcess, Pageable pageable, Integer type) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;
}
