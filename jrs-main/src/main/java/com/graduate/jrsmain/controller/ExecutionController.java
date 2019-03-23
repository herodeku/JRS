package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.repository.ExecutionRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Execution")
@Api(description = "操作执行信息公开网")
public class ExecutionController {

    @Autowired
    private ExecutionRepository executionRepository;
    
}
