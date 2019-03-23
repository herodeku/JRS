package com.graduate.jrsmain.repository;

import com.graduate.jrsmain.bean.Judgment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface JudgmentRepository extends ElasticsearchRepository<Judgment,String> {

}
