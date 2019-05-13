package com.graduate.jrsmain.util;

import com.graduate.jrsmain.vo.AdvJudgment;
import com.graduate.jrsmain.vo.AdvProcess;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.InternalTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class EsUtil {


    public static <T> List<T> search(ElasticsearchRepository<T,String> repository, QueryBuilder builder, Pageable pageable){
        Iterable<T> iterable;
        if(builder == null){
            iterable = repository.findAll(pageable);
        }else{
            iterable = repository.search(builder,pageable);
        }
        Iterator<T> iterator = iterable.iterator();
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }
    public static <T> Integer searchNum(ElasticsearchRepository<T,String> repository, QueryBuilder builder){
        Iterable<T> judgments = repository.search(builder);
        int num=0;
        for (T judgment : judgments) {
            num++;
        }
        return num;
    }
    private static void buildBoolQueryBuilder(BoolQueryBuilder queryBuilder, String message, String... parameters){
        for(String parameter : parameters){
            //短语匹配
            queryBuilder.should(QueryBuilders.matchPhraseQuery(parameter,message));
        }
    }
    private static void buildAdvBoolQueryBuilder(BoolQueryBuilder queryBuilder, Map<String, Object> map){
        for(Map.Entry<String,Object> entitySet: map.entrySet()){
            if(StringUtils.isNotBlank((String) entitySet.getValue())){
                queryBuilder.must(QueryBuilders.matchPhraseQuery(entitySet.getKey(),entitySet.getValue()));
            }
        }
        if(!queryBuilder.hasClauses()){
            throw new LawException(ResultCode.QUERYBUILDER_NULL);
        }
    }
    private static  <T> Map<String,Object> getMethod(T t){
        Map<String, Object> hashMap = new HashMap<>();
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field:fields){
                if(!field.getName().contains("LT") && !field.getName().contains("GTE")){
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),clazz);
                    Method readMethod = propertyDescriptor.getReadMethod();
                    Object invoke = readMethod.invoke(t);
                    hashMap.put(field.getName(),invoke);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return hashMap;
    }
    public static QueryBuilder createJudgmentBoolQueryBuilder(String message){
        BoolQueryBuilder bollQueryBuilder = new BoolQueryBuilder();
        if(DateUtil.judgeDate(message)){
            bollQueryBuilder.should(QueryBuilders.matchQuery("releaseDate",message));
        }
        EsUtil.buildBoolQueryBuilder(bollQueryBuilder,message, "judgeProcess", "caseNum", "caseName","courtName","judgeContent");
        return bollQueryBuilder;
    }
    public static <T> QueryBuilder createAdvJudgmentBoolQueryBuilder(AdvJudgment advJudgment){
        BoolQueryBuilder bollQueryBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotBlank(advJudgment.getJudgeDateGTE())) {
            bollQueryBuilder.must(QueryBuilders.rangeQuery("judgeDate").gte(advJudgment.getJudgeDateGTE()).lt(advJudgment.getJudgeDateLT()));
        }
        EsUtil.buildAdvBoolQueryBuilder(bollQueryBuilder,EsUtil.getMethod(advJudgment));
        return bollQueryBuilder;
    }
    public static QueryBuilder createAdvProcessBoolQueryBuilder(AdvProcess advProcess){
        BoolQueryBuilder bollQueryBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotBlank(advProcess.getReleaseDateGTE())){
            bollQueryBuilder.must(QueryBuilders.rangeQuery("releaseDate").gte(advProcess.getReleaseDateGTE()).lt(advProcess.getReleaseDateLT()));
        }
        EsUtil.buildAdvBoolQueryBuilder(bollQueryBuilder,EsUtil.getMethod(advProcess));
        return bollQueryBuilder;
    }
    public static QueryBuilder createProcessBoolQueryBuilder(String message){
        BoolQueryBuilder bollQueryBuilder = new BoolQueryBuilder();
//        if(DateUtil.judgeDate(message) ==true){
//            boolQueryBuilder.should(QueryBuilders.matchQuery("judgeDate",message));
//        }
        EsUtil.buildBoolQueryBuilder(bollQueryBuilder,message, "filename","content");
        return bollQueryBuilder;
    }
    public static QueryBuilder createExecutionBoolQueryBuilder(String message){
        BoolQueryBuilder bollQueryBuilder = new BoolQueryBuilder();
        EsUtil.buildBoolQueryBuilder(bollQueryBuilder,message, "title", "content");
        return bollQueryBuilder;
    }
    public static QueryBuilder createVideoBoolQueryBuilder(String message){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, "courtName", "caseNo", "title","description","judge");
        return boolQueryBuilder;
    }
    public static QueryBuilder createMixBoolQueryBuilder(String message){
        BoolQueryBuilder bollQueryBuilder = new BoolQueryBuilder();
        EsUtil.buildBoolQueryBuilder(bollQueryBuilder,message,
                "judgeProcess", "caseNum", "caseName","courtName","judgeContent","title","content","filename","caseNo","description","judge");
        return bollQueryBuilder;
    }
    public static SearchQuery aggregationCount(QueryBuilder queryBuilder,String filed,String index,String type){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();//查询条件生成器
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        nativeSearchQueryBuilder.withIndices(index).withTypes(type);
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("extendGroup").field(filed));
        return nativeSearchQueryBuilder.build();
    }
    public static  Map<Object,Long> aggregationQuery(SearchQuery searchQuery,ElasticsearchTemplate elasticsearchTemplate){
        Map<Object, Long> hashMap = new HashMap<>();
        Aggregations query = elasticsearchTemplate.query(searchQuery, (SearchResponse response) -> {
            return response.getAggregations();
        });
        Map<String, Aggregation> stringAggregationMap = query.asMap();
        InternalTerms stringTerms = (InternalTerms) stringAggregationMap.get("extendGroup");
        List<Terms.Bucket> buckets = stringTerms.getBuckets();
        for(Terms.Bucket b:buckets){
            hashMap.put(b.getKey(), b.getDocCount());
        }
        return hashMap;
    }
}
