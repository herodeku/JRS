package com.graduate.jrses.controller;

import com.graduate.jrses.bean.Judgment;
import com.graduate.jrses.service.JudgmentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Judgment")
@Api(description = "存储裁判文书网信息")
public class JudgmentController {

    @Autowired
    private JudgmentService judgmentServiceImpl;

    @PostMapping("/StoreIndex")
    public String store(@RequestBody Judgment judgment){
        return judgmentServiceImpl.storeIndex(judgment);
    }


}
