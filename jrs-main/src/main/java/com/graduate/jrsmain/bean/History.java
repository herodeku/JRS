package com.graduate.jrsmain.bean;

public class History {
    private String id;
    private String indexId;
    private String username;
    private String historyFrom;
    private long triggerTime;

    public History() {
    }

    public History(String indexId, String username, String historyFrom, long triggerTime) {
        this.indexId = indexId;
        this.username = username;
        this.historyFrom = historyFrom;
        this.triggerTime = triggerTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHistoryFrom() {
        return historyFrom;
    }

    public void setHistoryFrom(String historyFrom) {
        this.historyFrom = historyFrom;
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(long triggerTime) {
        this.triggerTime = triggerTime;
    }
}
