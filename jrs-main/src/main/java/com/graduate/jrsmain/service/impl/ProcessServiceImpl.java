package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.ProcessCase;
import com.graduate.jrsmain.bean.ProcessGuide;
import com.graduate.jrsmain.bean.ProcessTrialReport;
import com.graduate.jrsmain.repository.ProcessCaseRepository;
import com.graduate.jrsmain.repository.ProcessGuideRepository;
import com.graduate.jrsmain.repository.ProcessTrialReportRepository;
import com.graduate.jrsmain.service.ProcessService;
import com.graduate.jrsmain.util.DateUtil;
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
    private ProcessCaseRepository processCaseRepository;

    @Autowired
    private ProcessTrialReportRepository processTrialReportRepository;

    @Autowired
    private ProcessGuideRepository processGuideRepository;


    @Override
    public List<ProcessGuide> searchGuide(String message, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(DateUtil.judgeDate(message) ==true){
            boolQueryBuilder.should(QueryBuilders.matchQuery("judgeDate",message));
        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"filename", "province", "unit"});
        return EsUtil.search(processGuideRepository,boolQueryBuilder,pageable);
    }

    @Override
    public List<ProcessCase> searchCase(String message, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(DateUtil.judgeDate(message) ==true){
            boolQueryBuilder.should(QueryBuilders.matchQuery("judgeDate",message));
        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"filename", "unit", "content"});
        return EsUtil.search(processCaseRepository,boolQueryBuilder,pageable);
    }

    @Override
    public List<ProcessTrialReport> searchTriaReport(String message, Pageable pageable) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(DateUtil.judgeDate(message) ==true){
            boolQueryBuilder.should(QueryBuilders.matchQuery("judgeDate",message));
        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"filename", "caseType", "province", "unit", "content"});
        return EsUtil.search(processTrialReportRepository,boolQueryBuilder,pageable);
    }

    @Override
    public Object advSearch(AdvProcess advProcess, Pageable pageable, Integer type) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotBlank(advProcess.getReleaseDateGTE())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("releaseDate").gte(advProcess.getReleaseDateGTE()).lt(advProcess.getReleaseDateLT()));
        }
        EsUtil.buildAdvBoolQueryBuilder(boolQueryBuilder,EsUtil.getMethod(advProcess));
        if(type==0){
            return EsUtil.search(processCaseRepository,boolQueryBuilder,pageable);
        }else if(type==1){
            return EsUtil.search(processGuideRepository,boolQueryBuilder,pageable);
        }else {
            return EsUtil.search(processTrialReportRepository,boolQueryBuilder,pageable);
        }
    }
}
