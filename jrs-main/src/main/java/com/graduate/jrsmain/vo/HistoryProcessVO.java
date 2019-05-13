package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Process;

public class HistoryProcessVO {

    private Process process;
    private String from;
    private Long triggerTime;

    public HistoryProcessVO(Process process, String from, Long triggerTime) {
        this.process = process;
        this.from = from;
        this.triggerTime = triggerTime;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
