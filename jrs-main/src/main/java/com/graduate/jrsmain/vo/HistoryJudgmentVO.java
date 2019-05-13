package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Judgment;

public class HistoryJudgmentVO{

    private Judgment judgment;
    private String from;
    private Long triggerTime;

    public HistoryJudgmentVO(Judgment judgment, String from, Long triggerTime) {
        this.judgment = judgment;
        this.from = from;
        this.triggerTime = triggerTime;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Judgment getJudgment() {
        return judgment;
    }

    public void setJudgment(Judgment judgment) {
        this.judgment = judgment;
    }
}
