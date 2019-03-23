package com.graduate.jrsmain.repository;

import com.graduate.jrsmain.bean.Execution;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ExecutionRepository extends ElasticsearchRepository<Execution,String> {
}
