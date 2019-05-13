package com.graduate.jrsmain.util;

import com.graduate.jrsmain.vo.AdvJudgment;
import com.graduate.jrsmain.vo.AdvProcess;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
        Iterator<T> iterator = judgments.iterator();
        while (iterator.hasNext()){
            iterator.next();
            num++;
        }
        return num;
    }
    public static void buildBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder,String message,String ...parameters){
        for(String parameter : parameters){
            boolQueryBuilder.should(QueryBuilders.matchQuery(parameter,message));
        }
    }
    public static void buildAdvBoolQueryBuilder(BoolQueryBuilder boolQueryBuilder,Map<String,Object> map){
        for(Map.Entry<String,Object> entitySet: map.entrySet()){
            if(StringUtils.isNotBlank((String) entitySet.getValue())){
                System.out.println(entitySet.getKey()+":"+entitySet.getValue());
                boolQueryBuilder.must(QueryBuilders.matchQuery(entitySet.getKey(),entitySet.getValue()));
            }
        }
    }
    public static  <T> Map<String,Object> getMethod(T t){
        Map<String, Object> hashMap = new HashMap<>();
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field:fields){
                if(field.getName().indexOf("LT")==-1&&field.getName().indexOf("GTE")==-1){
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
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(DateUtil.judgeDate(message)==true){
            boolQueryBuilder.should(QueryBuilders.matchQuery("releaseDate",message));
        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"judgeProcess", "caseNum", "caseName","courtName","judgeContent"});
        return boolQueryBuilder;
    }
    public static QueryBuilder createAdvJudgmentBoolQueryBuilder(AdvJudgment advJudgment){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotBlank(advJudgment.getJudgeDateGTE())) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("judgeDate").gte(advJudgment.getJudgeDateGTE()).lt(advJudgment.getJudgeDateLT()));
        }
        EsUtil.buildAdvBoolQueryBuilder(boolQueryBuilder,EsUtil.getMethod(advJudgment));
        return boolQueryBuilder;
    }
    public static QueryBuilder createProcessBoolQueryBuilder(String message){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        if(DateUtil.judgeDate(message) ==true){
//            boolQueryBuilder.should(QueryBuilders.matchQuery("judgeDate",message));
//        }
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"filename","content"});
        return boolQueryBuilder;
    }
    public static QueryBuilder createAdvProcessBoolQueryBuilder(AdvProcess advProcess){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(StringUtils.isNotBlank(advProcess.getReleaseDateGTE())){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("releaseDate").gte(advProcess.getReleaseDateGTE()).lt(advProcess.getReleaseDateLT()));
        }
        try {
            EsUtil.buildAdvBoolQueryBuilder(boolQueryBuilder,EsUtil.getMethod(advProcess));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boolQueryBuilder;
    }
    public static QueryBuilder createExecutionBoolQueryBuilder(String message){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        EsUtil.buildBoolQueryBuilder(boolQueryBuilder,message, new String[]{"title", "content"});
        return boolQueryBuilder;
    }
}
