package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.repository.JudgmentRepository;
import com.graduate.jrsmain.service.JudgmentService;
import com.graduate.jrsmain.util.ResultUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/Judgment")
@Api(description = "操作裁判文书网")
public class JudgmentController {

    @Autowired
    private JudgmentRepository judgmentRepository;

    @Autowired
    private JudgmentService judgmentService;

    Logger logger = LoggerFactory.getLogger(JudgmentController.class);

    @ApiOperation(value = "简单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/simpleSearch/{message}/{page}/{size}")
    public ResultUtil simpleSearch(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
            logger.info("simpleSearch"+user.getAuthority()+":"+message);
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(judgmentService.simpleSearch(message,pageRequest));
    }

    @ApiOperation(value = "获取所有文书")
    @GetMapping("getAllJudgment")
    public ResultUtil getAllJudgment(){
        return ResultUtil.success(judgmentService.getAllJudgment());
    }

    @GetMapping("deleteAllJudgment")
    public void deleteAllJudgment(){
        judgmentService.deleteAllJudgment();
    }
    @GetMapping("/saveJudgment")
    public void saveCivil(){
        judgmentService.saveCivil();
    }
}
