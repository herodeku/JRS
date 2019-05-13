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

    public Map<String, Map<Object, Long>>  aggregationCount(QueryBuilder queryBuilder, ElasticsearchTemplate elasticsearchTemplate,String index,String t,String... types) {
        Map<String,Map<Object, Long>> mmp = new HashMap<>();
        SearchQuery searchQuery;
        for(String type:types){
            if(type.equals("courtName")){
                //对法院等级进行单独处理
                searchQuery = EsUtil.aggregationCount(queryBuilder,"courtName",index,t);
                mmp.put("courtName",ClassifyUtil.classifyCourtName(EsUtil.aggregationQuery(searchQuery, elasticsearchTemplate)));
            }else if(type.equals("year")){
                Map<Object,Long> m = new TreeMap<>((Object o1,Object o2)->{
                    Long s1 = (Long) o1;
                    Long s2 = (Long) o2;
                    return s1.compareTo(s2);
                });
                searchQuery = EsUtil.aggregationCount(queryBuilder,"year",index,t);
                m.putAll(EsUtil.aggregationQuery(searchQuery, elasticsearchTemplate));
                mmp.put("year", m);
            }else {
                searchQuery = EsUtil.aggregationCount(queryBuilder,type,index,t);
                mmp.put(type,(EsUtil.aggregationQuery(searchQuery, elasticsearchTemplate)));
            }
        }
        return mmp;
    }
}
