package com.graduate.jrses.repository;

import com.graduate.jrses.bean.Execution;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExecutionRepository extends ElasticsearchRepository<Execution,String> {
}
