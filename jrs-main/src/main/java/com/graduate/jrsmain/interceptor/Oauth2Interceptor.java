package com.graduate.jrsmain.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.service.UserService;
import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Oauth2Interceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String access_token = httpServletRequest.getHeader("access_token");
        if(StringUtils.isBlank(access_token)){
            LawUser user = new LawUser();
            user.setUsername("");
            user.setAuthority("visitor");
            httpServletRequest.setAttribute("user",user);
            return true;
        }else{
            RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
            OAuth2AccessToken oAuth2AccessToken = redisTokenStore.readAccessToken(access_token);
            if(oAuth2AccessToken==null){
                throw new LawException(ResultCode.LOGIN_ERROR);
            }
            OAuth2Authentication oAuth2Authentication = redisTokenStore.readAuthentication(oAuth2AccessToken);
            Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
            Object details = userAuthentication.getDetails();
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(details));
            String username = (String) jsonObject.get("username");
            LawUser user = userService.getUserByUserNameExcludePassWord(username);
            httpServletRequest.setAttribute("user",user);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
