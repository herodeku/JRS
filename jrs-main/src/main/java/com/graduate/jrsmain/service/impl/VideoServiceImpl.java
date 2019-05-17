package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.History;
import com.graduate.jrsmain.bean.Video;
import com.graduate.jrsmain.repository.VideoRepository;
import com.graduate.jrsmain.service.VideoService;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.util.PublicUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private PublicUtil publicUtil;

    private QueryBuilder queryBuilder;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Video findOne(String id,String username,boolean b) {
        Video one = videoRepository.findOne(id);
        if(b==true){
            publicUtil.storeHistory(one, id,username , this.getClass());
        }
        return one;
    }

    @Override
    public List<Video> findAll(Pageable pageable) {
        queryBuilder=null;
        return EsUtil.search(videoRepository, null, pageable);
    }

    @Override
    public List<Video> search(String message, Pageable pageable) {
        queryBuilder=EsUtil.createVideoBoolQueryBuilder(message);
        return EsUtil.search(videoRepository,queryBuilder,pageable);
    }

    @Override
    public Long searchNum() {
        return EsUtil.aggregationCount(elasticsearchTemplate,EsUtil.aggregation(queryBuilder,"","video","message","count"));
    }
}
