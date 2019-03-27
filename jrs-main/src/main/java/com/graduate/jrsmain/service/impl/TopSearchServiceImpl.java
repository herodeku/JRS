package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Judgment;
import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.*;
import com.graduate.jrsmain.util.RabbitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopSearchServiceImpl implements TopSearchService {

    @Autowired
    private JudgmentService judgmentServiceImpl;

    @Autowired
    private ExecutionService executionServiceImpl;

    @Autowired
    private ProcessService processServiceImpl;

    @Autowired
    private VideoService videoServiceImpl;

    @Autowired
    private RabbitUtil rabbitUtil;

    private int showNum=16;

    @Override
    public List<Object> recommend(LawUser user) {
        List<Object> objects = new ArrayList<>();
        List<String> list = new ArrayList<>();
        if(user.getAuthority().equals("populace")&&rabbitUtil.getPopulace()!=null){
            list = rabbitUtil.getPopulace();
        }else if(user.getAuthority().equals("judicialOfficer")&&rabbitUtil.getJudicialOfficer()!=null){
            list = rabbitUtil.getJudicialOfficer();
        }
        getLogMessage(list,objects);
        int objSize = objects.size();
        if(objSize<showNum){
            for (int i=0;i<showNum-objSize;i++){
                objects.add(judgmentServiceImpl.getAllJudgment().get(i));
                //如果没有大于showNum则从所有索引中找出size2个值补齐
            }
        }
        return objects;
    }

    public void  getLogMessage(List<String> list,List<Object> objects){
        for (String string:list){
            objects.add(judgmentServiceImpl.simpleSearch(string,new PageRequest(0,1)).get(0));
            objects.add(executionServiceImpl.simpleSearch(string,new PageRequest(0,1)).get(0));
            objects.add(processServiceImpl.simpleSearch(string,new PageRequest(0,1)).get(0));
            objects.add(videoServiceImpl.simpleSearch(string,new PageRequest(0,1)).get(0));
        }
    }
}
