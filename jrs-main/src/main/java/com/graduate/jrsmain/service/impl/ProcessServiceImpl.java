package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Process;
import com.graduate.jrsmain.repository.ProcessRepository;
import com.graduate.jrsmain.service.ProcessService;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.vo.AdvProcess;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Override
    public Process findOne(String id) {
        return processRepository.findOne(id);
    }

    @Override
    public List<Process>  search(String message, Pageable pageable) {
        return EsUtil.search(processRepository,EsUtil.createProcessBoolQueryBuilder(message),pageable);
    }

    @Override
    public List<Process> advSearch(AdvProcess advProcess, Pageable pageable){
        return EsUtil.search(processRepository,EsUtil.createAdvProcessBoolQueryBuilder(advProcess),pageable);
    }

    @Override
    public Integer searchNum(String message) {
        return EsUtil.searchNum(processRepository,EsUtil.createProcessBoolQueryBuilder(message));
    }

    @Override
    public Integer advSearchNum(AdvProcess advProcess) {
        return EsUtil.searchNum(processRepository,EsUtil.createAdvProcessBoolQueryBuilder(advProcess));
    }
}
