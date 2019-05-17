package com.graduate.jrsmain.controller;

import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.repository.ExecutionRepository;
import com.graduate.jrsmain.service.ExecutionService;
import com.graduate.jrsmain.util.PublicUtil;
import com.graduate.jrsmain.util.ResultUtil;
import com.graduate.jrsmain.util.StringToObjectUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/Execution")
@Api(description = "操作执行信息公开网")
public class ExecutionController {

    @Autowired
    private ExecutionService executionServiceImpl;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "根据Id查找索引")
    @GetMapping("/findOne/{id}")
    public ResultUtil findOne(
            @ApiParam(name = "id", value = "id", required = true)@PathVariable String id,
            Principal principal) {
        LawUser user = StringToObjectUtil.stringToLawUser(principal);
        return ResultUtil.success(executionServiceImpl.findOne(id,user.getUsername(),true));
    }

    @ApiOperation(value = "获取所有索引")
    @GetMapping("/findAll/{page}/{size}")
    public ResultUtil findAll(
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(executionServiceImpl.findAll(pageRequest));
    }

    @ApiOperation(value = "基础检索")
    @GetMapping("/simpleSearch/{message}/{page}/{size}")
    public ResultUtil simpleSearch(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size,
            Principal principal){
        LawUser user = StringToObjectUtil.stringToLawUser(principal);
        logger.info("SearchExecution"+"-Authority:"+user.getAuthority()+"-UserName:"+user.getUsername()+"-Message:"+message);
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(executionServiceImpl.search(message,pageRequest));
    }
    @ApiOperation(value = "检索到的条数")
    @GetMapping("/searchNum")
    public ResultUtil searchNum(){
        return ResultUtil.success(executionServiceImpl.searchNum());
    }
}
