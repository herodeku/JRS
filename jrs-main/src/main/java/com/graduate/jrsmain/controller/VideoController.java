package com.graduate.jrsmain.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.jrsmain.repository.VideoRepository;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/Video")
@Api(description = "操作庭审直播公开网")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/abc")
    public ResponseEntity<byte[]> test() throws IOException {
        File file = new File("C:\\Users\\hasee\\Desktop\\新建文本文档 (2).txt");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
