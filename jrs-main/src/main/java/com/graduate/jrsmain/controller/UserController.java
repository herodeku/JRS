package com.graduate.jrsmain.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.graduate.jrsmain.bean.CustomUserDetails;
import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.bean.User;
import com.graduate.jrsmain.service.UserService;
import com.graduate.jrsmain.util.ResultUtil;
import com.graduate.jrsmain.util.StringToObjectUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import sun.applet.Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.security.Principal;

@RestController
@RequestMapping("/User")
@Api(description = "操作用户")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @ApiOperation(value ="注册用户")
    @PostMapping("/register")
    public ResultUtil register(@ApiParam("sex只能传男或女," +
            "年龄," +
            "手机号,username(账号)前端需要进行合理的限制," +
            "authority只能传populace(民众),judicialOfficer(司法人员)")@RequestBody LawUser user){
        return ResultUtil.success(userService.register(user));
    }

    @ApiOperation(value ="登出")
    @GetMapping("/logout/{token}")
    public ResultUtil logout(@PathVariable String token){
        if(consumerTokenServices.revokeToken(token)){
            return ResultUtil.success("登陆成功");
        }else{
            return ResultUtil.error(null);
        }
    }
    @ApiOperation(value ="获取用户信息")
    @GetMapping("/getInfo")
    public ResultUtil getInfo(Principal principal){
        return ResultUtil.success(StringToObjectUtil.stringToLawUser(principal));
    }
}
