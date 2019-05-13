package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Video;

public class HistoryVideoVO {

    private Video video;
    private String from;
    private Long triggerTime;

    public HistoryVideoVO(Video video, String from, Long triggerTime) {
        this.video = video;
        this.from = from;
        this.triggerTime = triggerTime;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
