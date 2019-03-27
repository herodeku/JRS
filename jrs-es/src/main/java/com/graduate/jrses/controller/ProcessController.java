package com.graduate.jrses.controller;


import com.graduate.jrses.repository.ProcessRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Process")
@Api(description = "存储中国审判流程信息公开网信息")
public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;
}
