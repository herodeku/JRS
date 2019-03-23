package com.graduate.jrsmain.controller;


import com.graduate.jrsmain.repository.ProcessRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Process")
@Api(description = "操作中国审判流程信息公开网")
public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;
}
