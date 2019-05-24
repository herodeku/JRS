package com.graduate.jrses.service.impl;

import com.graduate.jrses.bean.Mix;
import com.graduate.jrses.repository.MixRepository;
import com.graduate.jrses.service.MixService;
import com.graduate.jrses.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MixServiceImpl implements MixService {

    @Autowired
    private MixRepository mixRepository;

    @Override
    public String storeIndex(Mix mix) {
        if(StringUtils.isNotBlank(mix.getId())){
            mixRepository.index(mix);
            return ResultUtil.SUCCESS;
        }else {
            return ResultUtil.ERROR;
        }
    }
}
