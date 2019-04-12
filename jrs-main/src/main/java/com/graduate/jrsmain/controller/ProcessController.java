package com.graduate.jrsmain.controller;


import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.ProcessService;
import com.graduate.jrsmain.util.ResultUtil;
import com.graduate.jrsmain.vo.AdvProcess;
import com.graduate.jrsmain.vo.ProcessVO;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/Process")
@Api(description = "操作中国审判流程信息公开网")
public class ProcessController {

    @Autowired
    private ProcessService processServiceImpl;

    private Logger logger = LoggerFactory.getLogger(JudgmentController.class);

    @ApiOperation(value = "案例基础检索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/simpleSearchCase/{message}/{page}/{size}")
    public ResultUtil simpleSearchCase(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        logger.info("Search-Judgement"+"Authority:"+user.getAuthority()+"Message:"+message);
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(processServiceImpl.searchCase(message,pageRequest));
    }
    @ApiOperation(value = "诉讼指南基础检索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/simpleSearchGuide/{message}/{page}/{size}")
    public ResultUtil simpleSearchGuide(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        logger.info("Search-Judgement"+"Authority:"+user.getAuthority()+"Message:"+message);
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(processServiceImpl.searchGuide(message,pageRequest));
    }
    @ApiOperation(value = "开庭报告基础检索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/simpleSearchTrialReport/{message}/{page}/{size}")
    public ResultUtil simpleSearchTrialReport(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        logger.info("Search-Judgement"+"Authority:"+user.getAuthority()+"Message:"+message);
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(processServiceImpl.searchTriaReport(message,pageRequest));
    }
    @ApiOperation(value = "高级检索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @PostMapping("/advSearch")
    public ResultUtil advSearch(
            @ApiParam(name = "message", value = "检索条件", required = true)@RequestBody ProcessVO processVO,
            @ApiIgnore @RequestAttribute(name = "user") LawUser user) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //如果filename为空就填content，如果content为空就填filename，如果都有就填content
        logger.info("Search-Judgement"+"Authority:"+user.getAuthority()+"Message:"+ (StringUtils.isNotBlank(processVO.getAdvProcess().getFileName())?processVO.getAdvProcess().getFileName():processVO.getAdvProcess().getContent()));
        PageRequest pageRequest = new PageRequest(processVO.getPage(), processVO.getSize());
        return ResultUtil.success(processServiceImpl.advSearch(processVO.getAdvProcess(),pageRequest,processVO.getType()));
    }
}
