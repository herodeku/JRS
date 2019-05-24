package com.graduate.jrses.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "mix",type = "message")
public class Mix {
    @Id
    @Field(type = FieldType.String,index = FieldIndex.not_analyzed)
    private String id;
    private String type;//video process judgment execution
    private String title;
    private String content;
    private String judgeContent;//裁判内容，简情
    private String judgeDate;
    private String judgeProcess;
    private String caseNum;//案号
    private String caseName;
    @Field(type = FieldType.String,index = FieldIndex.not_analyzed)
    private String courtName;
    @Field(type = FieldType.String,index = FieldIndex.not_analyzed)
    private String caseType;
    private String fileName;
    @Field(type = FieldType.String,index = FieldIndex.not_analyzed)
    private String province;
    private String unit;
    private String releaseDate;
    @Field(type = FieldType.String,index = FieldIndex.not_analyzed)
    private String programme;
    private String caseNo;
    private String description;
    private String liveUrl;
    private String videoThumbnail;
    private String judge;
    private String beginTime;
    @Field(type = FieldType.Date,format = DateFormat.year)
    private String year;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}
