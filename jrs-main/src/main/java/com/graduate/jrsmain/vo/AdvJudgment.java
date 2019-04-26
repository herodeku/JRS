package com.graduate.jrsmain.vo;

public class AdvJudgment {
    private String judgeContent;//查询内容
    private String judgeDateLT;//最大裁判日期
    private String judgeDateGTE;//最小裁判日期
    private String judgeProcess;//审判程序
    private String caseNum;//案号
    private String caseName;//案件名称
    private String courtName;//法院名称
    private String caseType;//案件类型

    public String getJudgeContent() {
        return judgeContent;
    }

    public void setJudgeContent(String judgeContent) {
        this.judgeContent = judgeContent;
    }

    public String getJudgeDateLT() {
        return judgeDateLT;
    }

    public void setJudgeDateLT(String judgeDateLT) {
        this.judgeDateLT = judgeDateLT;
    }

    public String getJudgeDateGTE() {
        return judgeDateGTE;
    }

    public void setJudgeDateGTE(String judgeDateGTE) {
        this.judgeDateGTE = judgeDateGTE;
    }

    public String getJudgeProcess() {
        return judgeProcess;
    }

    public void setJudgeProcess(String judgeProcess) {
        this.judgeProcess = judgeProcess;
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
