package com.graduate.jrses.repository;

import com.graduate.jrses.bean.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface VideoRepository extends ElasticsearchRepository<Video,String> {
}
