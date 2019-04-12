package com.graduate.jrses.controller;

import com.graduate.jrses.bean.ProcessCase;
import com.graduate.jrses.bean.ProcessGuide;
import com.graduate.jrses.bean.ProcessTrialReport;
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
    @PostMapping("/StoreCase")
    public String storeCase(@RequestBody ProcessCase processCase) {
        return processServiceImpl.storeCase(processCase);
    }

    @ApiOperation("存储诉讼指南")
    @PostMapping("/StoreGuide")
    public String storeGuid(@RequestBody ProcessGuide processGuide) {
        return processServiceImpl.storeGuide(processGuide);
    }

    @ApiOperation("存储开庭报告")
    @PostMapping("/StoreTrialReport")
    public String storeTrialReprot(@RequestBody ProcessTrialReport processTrialReport) {
        return processServiceImpl.storeTrialReport(processTrialReport);
    }
}
