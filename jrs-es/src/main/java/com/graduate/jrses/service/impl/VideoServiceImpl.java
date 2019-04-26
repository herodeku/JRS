package com.graduate.jrses.service.impl;


import com.graduate.jrses.bean.Video;
import com.graduate.jrses.repository.VideoRepository;
import com.graduate.jrses.service.VideoService;
import com.graduate.jrses.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public String storeIndex(Video video) {
        if(StringUtils.isNotBlank(video.getCaseId())){
            videoRepository.index(video);
            return ResultUtil.SUCCESS;
        }else {
            return ResultUtil.ERROR;
        }
    }
}
