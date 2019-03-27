package com.graduate.jrses.controller;

import com.graduate.jrses.repository.ExecutionRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Execution")
@Api(description = "存储执行信息公开网信息")
public class ExecutionController {

    @Autowired
    private ExecutionRepository executionRepository;
    
}
