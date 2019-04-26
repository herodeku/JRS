package com.graduate.jrsmain.vo;

public class JudgmentVO {

    private Integer page;
    private Integer size;
    private AdvJudgment advJudgment;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public AdvJudgment getAdvJudgment() {
        return advJudgment;
    }

    public void setAdvJudgment(AdvJudgment advJudgment) {
        this.advJudgment = advJudgment;
    }

    @Override
    public String toString() {
        return "JudgmentVO{" +
                "page=" + page +
                ", size=" + size +
                ", advJudgment=" + advJudgment +
                '}';
    }
}
