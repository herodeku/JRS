package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.HistoryService;
import com.graduate.jrsmain.util.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/History")
@Api(description = "历史纪录")
public class HistoryController {

    @Autowired
    private HistoryService historyServiceImpl;

    @ApiOperation(value = "清空历史记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/cleanHistory")
    public ResultUtil cleanHistory(
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        historyServiceImpl.deleteHistory(user.getUsername());
        return ResultUtil.success(null);
    }
    @ApiOperation(value = "获取历史记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/getHistory")
    public ResultUtil getHistory(
            @ApiIgnore @RequestAttribute(name = "user") LawUser user){
        return ResultUtil.success(historyServiceImpl.getHistory(user.getUsername()));
    }
}
