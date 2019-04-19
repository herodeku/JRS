package com.graduate.jrsmain.bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "judgment",type = "message")
public class Judgment implements Serializable {
    @Id
    private String id;//文书id
    private String judgeContent;
    private String judgeDate;
    private String judgeProcess;
    private String caseNum;//案号
    private String caseName;
    private String courtName;
    private String content;//具体内容
    private String caseType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJudgeContent() {
        return judgeContent;
    }

    public void setJudgeContent(String judgeContent) {
        this.judgeContent = judgeContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudgeDate() {
        return judgeDate;
    }

    public void setJudgeDate(String judgeDate) {
        this.judgeDate = judgeDate;
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
