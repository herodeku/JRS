package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Judgment;

public class HistoryJudgmentVO{

    private Judgment judgment;
    private String from;

    public HistoryJudgmentVO(Judgment judgment, String from) {
        this.judgment = judgment;
        this.from = from;
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
