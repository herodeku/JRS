package com.graduate.jrsmain.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.UserService;
import com.graduate.jrsmain.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@Api(description = "操作用户")
public class UserController {

    @Autowired
    private UserService userService;

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

    @ApiOperation(value ="通过token获取用户信息")
    @GetMapping("/getUserByToken/{token}")
    public ResultUtil accessToken(@PathVariable String token){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        OAuth2AccessToken oAuth2AccessToken = redisTokenStore.readAccessToken(token);
        OAuth2Authentication oAuth2Authentication = redisTokenStore.readAuthentication(oAuth2AccessToken);
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        Object details = userAuthentication.getDetails();
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(details));
        String username = (String) jsonObject.get("username");
        return ResultUtil.success(userService.getUserByUserNameExcludePassWord(username));
    }
}
