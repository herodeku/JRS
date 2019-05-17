package com.graduate.jrsmain.util;

import com.graduate.jrsmain.bean.History;
import com.graduate.jrsmain.service.HistoryService;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
public class PublicUtil {

    @Autowired
    private HistoryService historyServiceImpl;

    public void storeHistory(Object obj,String id,String username,Class clazz){
        if(obj==null){
            throw new LawException(ResultCode.INDEX_NOT_FOUND);
        }
         historyServiceImpl.addHistory(new History(id,username,clazz.getSimpleName(),new Date().getTime()));
    }

    public Map<String, Map<Object, Long>>  aggregationTermsClassify(QueryBuilder queryBuilder, ElasticsearchTemplate elasticsearchTemplate,String index,String type,String... types) {
        Map<String,Map<Object, Long>> mmp = new HashMap<>();
        SearchQuery searchQuery;
        for(String t:types){
            if(t.equals("courtName")){
                //对法院等级进行单独处理
                searchQuery = EsUtil.aggregation(queryBuilder,"courtName",index,type,"terms");
                mmp.put("courtName",ClassifyUtil.classifyCourtName(EsUtil.aggregationTerms(searchQuery, elasticsearchTemplate)));
            }else if(t.equals("year")){
                Map<Object,Long> m = new TreeMap<>((Object o1,Object o2)->{
                    Long s1 = (Long) o1;
                    Long s2 = (Long) o2;
                    return s1.compareTo(s2);
                });
                searchQuery = EsUtil.aggregation(queryBuilder,"year",index,type,"terms");
                m.putAll(EsUtil.aggregationTerms(searchQuery, elasticsearchTemplate));
                mmp.put("year", m);
            }else {
                searchQuery = EsUtil.aggregation(queryBuilder,t,index,type,"terms");
                mmp.put(t,(EsUtil.aggregationTerms(searchQuery, elasticsearchTemplate)));
            }
        }
        return mmp;
    }
}
