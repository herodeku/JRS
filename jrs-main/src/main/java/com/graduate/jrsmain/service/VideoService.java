package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Video;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoService {
    Video findOne(String id);
    List<Video> search(String message, Pageable pageable);
    //List<Video> advSearch(AdvJudgment advJudgment, Pageable pageable);
}
