package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.repository.VideoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Video")
@Api(description = "操作庭审直播公开网")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;
}
