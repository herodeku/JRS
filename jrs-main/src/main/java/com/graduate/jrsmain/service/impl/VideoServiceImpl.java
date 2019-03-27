package com.graduate.jrsmain.service.impl;

import com.graduate.jrsmain.bean.Video;
import com.graduate.jrsmain.service.VideoService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public List<Video> simpleSearch(String message, Pageable pageable) {
        List<Video> videos = new ArrayList<>();
        return videos;
    }
}
