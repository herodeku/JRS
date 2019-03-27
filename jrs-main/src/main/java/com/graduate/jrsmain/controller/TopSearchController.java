package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.*;
import com.graduate.jrsmain.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/TopSearch")
@Api(description = "热度推荐")
public class TopSearchController {

    @Autowired
    private TopSearchService topSearchServiceImpl;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "access_token", required = true) })
    @GetMapping("/recommend")
    public ResultUtil recommend(@ApiIgnore @RequestAttribute(name = "user") LawUser user){
        return ResultUtil.success(topSearchServiceImpl.recommend(user));
    }
}
