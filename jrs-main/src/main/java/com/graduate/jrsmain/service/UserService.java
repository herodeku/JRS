package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User getUserByUserName(String s);
    public LawUser getUserByUserNameExcludePassWord(String s);
    public String register(LawUser user);
}
