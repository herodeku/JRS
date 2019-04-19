package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Video;
import com.graduate.jrsmain.repository.VideoRepository;
import com.graduate.jrsmain.service.VideoService;
import com.graduate.jrsmain.util.EsUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video findOne(String id) {
        return videoRepository.findOne(id);
    }

    @Override
    public List<Video> search(String message, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        if(DateUtil.judgeDate(message)==true){
//            boolQueryBuilder.should(QueryBuilders.matchQuery("beginTime",message));
//        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"courtName", "caseNo", "title","description","judge"});
        return EsUtil.search(videoRepository,boolQueryBuilder,pageable);
    }
}
