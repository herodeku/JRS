package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Process;
import com.graduate.jrsmain.repository.ProcessRepository;
import com.graduate.jrsmain.service.ProcessService;
import com.graduate.jrsmain.util.EsUtil;
import com.graduate.jrsmain.vo.AdvProcess;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Override
    public Process findOne(String id) {
        return processRepository.findOne(id);
    }

    @Override
    public List<Process>  search(String message, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        if(DateUtil.judgeDate(message) ==true){
//            boolQueryBuilder.should(QueryBuilders.matchQuery("judgeDate",message));
//        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"filename","content"});
        return EsUtil.search(processRepository,boolQueryBuilder,pageable);
    }

    @Override
    public List<Process> advSearch(AdvProcess advProcess, Pageable pageable) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotBlank(advProcess.getReleaseDateGTE())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("releaseDate").gte(advProcess.getReleaseDateGTE()).lt(advProcess.getReleaseDateLT()));
        }
        EsUtil.buildAdvBoolQueryBuilder(boolQueryBuilder,EsUtil.getMethod(advProcess));
        return EsUtil.search(processRepository,boolQueryBuilder,pageable);
    }
}
