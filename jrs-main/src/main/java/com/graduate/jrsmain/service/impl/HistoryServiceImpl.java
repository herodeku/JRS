package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.History;
import com.graduate.jrsmain.dao.HistoryMapper;
import com.graduate.jrsmain.service.*;
import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import com.graduate.jrsmain.vo.HistoryExecutionVO;
import com.graduate.jrsmain.vo.HistoryJudgmentVO;
import com.graduate.jrsmain.vo.HistoryProcessVO;
import com.graduate.jrsmain.vo.HistoryVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;

    private final JudgmentService judgmentServiceImpl;

    private final ExecutionService executionServiceImpl;

    private final ProcessService processServiceImpl;

    private final VideoService videoServiceImpl;

    @Autowired
    public HistoryServiceImpl(HistoryMapper historyMapper, JudgmentService judgmentServiceImpl, ExecutionService executionServiceImpl, ProcessService processServiceImpl, VideoService videoServiceImpl) {
        this.historyMapper = historyMapper;
        this.judgmentServiceImpl = judgmentServiceImpl;
        this.executionServiceImpl = executionServiceImpl;
        this.processServiceImpl = processServiceImpl;
        this.videoServiceImpl = videoServiceImpl;
    }

    @Override
    public void addHistory(History history) {
        history.setId(UUID.randomUUID().toString());
        if(historyMapper.addHistory(history)==0){
            throw new LawException(ResultCode.HISTORY_NOT_STORE);
        }
    }

    @Override
    public void deleteHistory(String userName) {
        if(historyMapper.deleteHistory(userName)==0){
            throw new LawException(ResultCode.HISTORY_NOT_DELETE);
        }
    }

    @Override
    public List<Object> getHistory(String userName) {
        List<History> histories = historyMapper.getHistory(userName).
                stream().sorted((o1,o2)->{
            return Long.compare(o2.getTriggerTime(), o1.getTriggerTime());
        }).collect(Collectors.toList());
        List<Object> objects = new ArrayList<>();
        for(History history:histories){
            switch (history.getHistoryFrom()) {
                case "JudgmentServiceImpl":
                    objects.add(new HistoryJudgmentVO(judgmentServiceImpl.findOne(history.getIndexId(), userName, false), history.getHistoryFrom(),history.getTriggerTime()));
                    break;
                case "VideoServiceImpl":
                    objects.add(new HistoryVideoVO(videoServiceImpl.findOne(history.getIndexId(), userName, false), history.getHistoryFrom(),history.getTriggerTime()));
                    break;
                case "ProcessServiceImpl":
                    objects.add(new HistoryProcessVO(processServiceImpl.findOne(history.getIndexId(), userName, false), history.getHistoryFrom(),history.getTriggerTime()));
                    break;
                case "ExecutionServiceImpl":
                    objects.add(new HistoryExecutionVO(executionServiceImpl.findOne(history.getIndexId(), userName, false), history.getHistoryFrom(),history.getTriggerTime()));
                    break;
            }
        }
        return objects;
    }
}
