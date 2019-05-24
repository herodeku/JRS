package com.graduate.jrses.repository;

import com.graduate.jrses.bean.Mix;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MixRepository extends ElasticsearchRepository<Mix,String> {
}
