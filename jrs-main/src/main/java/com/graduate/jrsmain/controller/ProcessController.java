package com.graduate.jrsmain.controller;


import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.ProcessService;
import com.graduate.jrsmain.util.ResultUtil;
import com.graduate.jrsmain.util.StringToObjectUtil;
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
import java.security.Principal;

@RestController
@RequestMapping("/Process")
@Api(description = "操作中国审判流程信息公开网")
public class ProcessController {

    @Autowired
    private ProcessService processServiceImpl;

    private Logger logger = LoggerFactory.getLogger(JudgmentController.class);

    @ApiOperation(value = "根据Id查找索引")
    @GetMapping("/findOne/{id}")
    public ResultUtil findOne(
            @ApiParam(name = "id", value = "id", required = true)@PathVariable String id,
            Principal principal) {
        LawUser user = StringToObjectUtil.stringToLawUser(principal);
        return ResultUtil.success(processServiceImpl.findOne(id,user.getUsername(),true));
    }

    @ApiOperation(value = "获取所有索引")
    @GetMapping("/findAll/{page}/{size}")
    public ResultUtil findAll(
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size
    ) {
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(processServiceImpl.findAll(pageRequest));
    }

    @ApiOperation(value = "基础检索")
    @GetMapping("/simpleSearch/{message}/{page}/{size}")
    public ResultUtil simpleSearch(
            @ApiParam(name = "message", value = "查询内容", required = true)@PathVariable String message,
            @ApiParam(name = "page", value = "第page页（从0开始）", required = true)@PathVariable Integer page,
            @ApiParam(name = "size", value = "每页size条数据", required = true)@PathVariable Integer size,
            Principal principal){
        LawUser user = StringToObjectUtil.stringToLawUser(principal);
        logger.info("Search-Process"+"-Authority:"+user.getAuthority()+"-UserName:"+user.getUsername()+"-Message:"+message);
        PageRequest pageRequest = new PageRequest(page, size);
        return ResultUtil.success(processServiceImpl.search(message,pageRequest));
    }
    @ApiOperation(value = "高级检索")
    @PostMapping("/advSearch")
    public ResultUtil advSearch(
            @ApiParam(name = "message", value = "检索条件", required = true)@RequestBody ProcessVO processVO,
            Principal principal){
        LawUser user = StringToObjectUtil.stringToLawUser(principal);
        //如果filename为空就填content，如果content为空就填filename，如果都有就填content
        logger.info("Search-Judgement"+"-Authority:"+user.getAuthority()+"-UserName:"+user.getUsername()+"-Message:"+(StringUtils.isNotBlank(processVO.getAdvProcess().getFileName())?processVO.getAdvProcess().getFileName():processVO.getAdvProcess().getContent()));
        PageRequest pageRequest = new PageRequest(processVO.getPage(), processVO.getSize());
        return ResultUtil.success(processServiceImpl.advSearch(processVO.getAdvProcess(),pageRequest));
    }
    @ApiOperation(value = "/检索分类")
    @GetMapping("/aggregation")
    public ResultUtil aggregation(){
        processServiceImpl.aggregationCount();
        return ResultUtil.success(null);
    }
    @ApiOperation(value = "检索到的总数")
    @GetMapping("/searchNum")
    public ResultUtil searchNum(){
        return ResultUtil.success(processServiceImpl.searchNum());
    }
}
