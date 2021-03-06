package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.HistoryService;
import com.graduate.jrsmain.util.ResultUtil;
import com.graduate.jrsmain.util.StringToObjectUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping("/History")
@Api(description = "历史纪录")
public class HistoryController {

    @Autowired
    private HistoryService historyServiceImpl;

    @ApiOperation(value = "清空历史记录")
    @GetMapping("/cleanHistory")
    public ResultUtil cleanHistory(Principal principal){
        LawUser user = StringToObjectUtil.stringToLawUser(principal);
        historyServiceImpl.deleteHistory(user.getUsername());
        return ResultUtil.success(null);
    }
    @ApiOperation(value = "获取历史记录")
    @GetMapping("/getHistory")
    public ResultUtil getHistory(Principal principal){
        LawUser user = StringToObjectUtil.stringToLawUser(principal);
        return ResultUtil.success(historyServiceImpl.getHistory(user.getUsername()));
    }
}
