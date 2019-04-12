package com.graduate.jrses.controller;

import com.graduate.jrses.bean.Execution;
import com.graduate.jrses.service.ExecutionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Execution")
@Api(description = "存储执行信息公开网信息")
public class ExecutionController {

    @Autowired
    private ExecutionService executionServiceImpl;

    @PostMapping("/StoreIndex")
    public String store(@RequestBody Execution execution){
        return executionServiceImpl.storeIndex(execution);
    }

}
