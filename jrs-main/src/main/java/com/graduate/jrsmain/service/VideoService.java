package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Video;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoService {
    List<Video> simpleSearch(String message, Pageable pageable);
}
