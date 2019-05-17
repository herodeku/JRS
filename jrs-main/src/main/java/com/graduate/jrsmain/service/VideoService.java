package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.Video;
import com.graduate.jrsmain.vo.AdvJudgment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoService {
    Video findOne(String id,String username,boolean b);
    List<Video> findAll(Pageable pageable);
    List<Video> search(String message, Pageable pageable);
    Long searchNum();
}
