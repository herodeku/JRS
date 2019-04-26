package com.graduate.jrses.service.impl;

import com.graduate.jrses.bean.Process;
import com.graduate.jrses.repository.ProcessRepository;
import com.graduate.jrses.service.ProcessService;
import com.graduate.jrses.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Override
    public String store(Process process) {
        if(StringUtils.isNotBlank(process.getId())){
            processRepository.index(process);
            return ResultUtil.SUCCESS;
        }else {
            return ResultUtil.ERROR;
        }
    }
}
