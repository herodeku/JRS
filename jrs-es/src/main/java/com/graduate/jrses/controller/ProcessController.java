package com.graduate.jrses.controller;

import com.graduate.jrses.bean.Process;
import com.graduate.jrses.service.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Process")
@Api(description = "存储中国审判流程信息公开网信息")
public class ProcessController {

    @Autowired
    private ProcessService processServiceImpl;

    @ApiOperation("存储案例")
    @PostMapping("/StoreIndex")
    public String storeCase(@RequestBody Process process) {
        return processServiceImpl.store(process);
    }

}
