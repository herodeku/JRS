package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Execution;

public class HistoryExecutionVO{

    private Execution execution;
    private String from;

    public HistoryExecutionVO(Execution execution, String from) {
        this.execution = execution;
        this.from = from;
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
