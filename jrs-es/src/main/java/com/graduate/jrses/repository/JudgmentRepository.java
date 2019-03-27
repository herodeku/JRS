package com.graduate.jrses.repository;

import com.graduate.jrses.bean.Judgment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface JudgmentRepository extends ElasticsearchRepository<Judgment,String> {

}
