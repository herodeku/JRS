package com.graduate.jrses.controller;

import com.graduate.jrses.bean.Video;
import com.graduate.jrses.service.VideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Video")
@Api(description = "存储庭审直播公开网信息")
public class VideoController {

    @Autowired
    private VideoService videoServiceImpl;

    @PostMapping("/StoreIndex")
    public String store(@RequestBody Video video){
        return videoServiceImpl.storeIndex(video);
    }

}
