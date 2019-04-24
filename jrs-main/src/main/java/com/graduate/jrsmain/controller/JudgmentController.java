package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.JudgmentService;
import com.graduate.jrsmain.util.ResultUtil;
import com.graduate.jrsmain.vo.AdvJudgment;
import com.graduate.jrsmain.vo.JudgmentVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;


@RestController
@RequestMapping("/Judgment")
@Api(description = "操作裁判文书网")
public class JudgmentController {

    @Autowired
    private JudgmentService judgmentServiceImpl;

    private Logger logger = LoggerFactory.getLogger(JudgmentController.class);

    @ApiOperation(value = "根据Id查找索引")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/findOne/{id}")
    public ResultUtil findOne(
            @ApiParam(name = "id", value = "id", required = true)@PathVariable String id,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        return ResultUtil.success(judgmentServiceImpl.findOne(id));
    }

    @ApiOperation(value = "基础检索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/simpleSearch/{message}/{page}/{size}")
    public ResultUtil simpleSearch(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        logger.info("Search-Judgement"+"Authority:"+user.getAuthority()+"Message:"+message);
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(judgmentServiceImpl.search(message,pageRequest));
    }

    @ApiOperation(value = "高级检索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @PostMapping("/advSearch")
    public ResultUtil advSearch(
            @ApiParam(name = "message", value = "检索条件", required = true)@RequestBody JudgmentVO judgmentVO,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        logger.info("Search-Judgement"+"Authority:"+user.getAuthority()+"Message:"+ judgmentVO.getAdvJudgment().getJudgeContent());
        PageRequest pageRequest = new PageRequest(judgmentVO.getPage(), judgmentVO.getSize());
        return ResultUtil.success(judgmentServiceImpl.advSearch(judgmentVO.getAdvJudgment(),pageRequest));
    }

    @ApiOperation(value = "基础检索到的总数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/simpleSearchNum/{message}")
    public ResultUtil simpleSearchNum(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        return ResultUtil.success(judgmentServiceImpl.searchNum(message));
    }

    @ApiOperation(value = "高级检索到的总数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @PostMapping("/advSearchNum")
    public ResultUtil advSearchNum(
            @ApiParam(name = "message", value = "检索条件", required = true)@RequestBody JudgmentVO judgmentVO,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        return ResultUtil.success(judgmentServiceImpl.advSearchNum(judgmentVO.getAdvJudgment()));
    }

}
