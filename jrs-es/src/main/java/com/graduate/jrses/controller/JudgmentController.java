package com.graduate.jrses.controller;

import com.graduate.jrses.repository.JudgmentRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Judgment")
@Api(description = "存储裁判文书网信息")
public class JudgmentController {

    @Autowired
    private JudgmentRepository judgmentRepository;

}
