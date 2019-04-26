package com.graduate.jrsmain.vo;

public class AdvProcess {

    private String releaseDateLT;//最大裁判日期
    private String releaseDateGTE;//最小裁判日期
    private String content;
    private String fileName;
    private String programa;

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getReleaseDateLT() {
        return releaseDateLT;
    }

    public void setReleaseDateLT(String releaseDateLT) {
        this.releaseDateLT = releaseDateLT;
    }

    public String getReleaseDateGTE() {
        return releaseDateGTE;
    }

    public void setReleaseDateGTE(String releaseDateGTE) {
        this.releaseDateGTE = releaseDateGTE;
    }
}
