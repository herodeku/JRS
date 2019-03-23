package com.graduate.jrsmain.repository;

import com.graduate.jrsmain.bean.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VideoRepository extends ElasticsearchRepository<Video,String> {
}
