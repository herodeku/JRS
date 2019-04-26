package com.graduate.jrsmain.repository;

import com.graduate.jrsmain.bean.Process;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProcessRepository extends ElasticsearchRepository<Process,String> {
}
