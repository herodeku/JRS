package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Execution;

public class HistoryExecutionVO{

    private Execution execution;
    private String from;
    private Long triggerTime;

    public HistoryExecutionVO(Execution execution, String from, Long triggerTime) {
        this.execution = execution;
        this.from = from;
        this.triggerTime = triggerTime;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
