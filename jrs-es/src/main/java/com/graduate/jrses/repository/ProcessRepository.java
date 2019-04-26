package com.graduate.jrses.repository;

import com.graduate.jrses.bean.Process;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProcessRepository extends ElasticsearchRepository<Process,String> {
}
