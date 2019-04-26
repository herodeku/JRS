package com.graduate.jrsmain.vo;

import com.graduate.jrsmain.bean.Video;

public class HistoryVideoVO {

    private Video video;
    private String from;

    public HistoryVideoVO(Video video, String from) {
        this.video = video;
        this.from = from;
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
