package com.graduate.jrses.service.impl;

import com.graduate.jrses.bean.Judgment;
import com.graduate.jrses.repository.JudgmentRepository;
import com.graduate.jrses.service.JudgmentService;
import com.graduate.jrses.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgmentServiceImpl implements JudgmentService {

    @Autowired
    private JudgmentRepository judgmentRepository;

    @Override
    public String storeIndex(Judgment judgment) {
        if(StringUtils.isNotBlank(judgment.getId())){
            judgmentRepository.index(judgment);
            return ResultUtil.SUCCESS;
        }else {
            return ResultUtil.ERROR;
        }
    }
}
