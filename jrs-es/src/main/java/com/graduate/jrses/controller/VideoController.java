package com.graduate.jrses.controller;

import com.graduate.jrses.repository.VideoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Video")
@Api(description = "存储庭审直播公开网信息")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;
}
