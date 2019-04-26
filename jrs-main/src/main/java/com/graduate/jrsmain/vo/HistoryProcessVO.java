package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Process;

public class HistoryProcessVO {

    private Process process;
    private String from;

    public HistoryProcessVO(Process process, String from) {
        this.process = process;
        this.from = from;
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
