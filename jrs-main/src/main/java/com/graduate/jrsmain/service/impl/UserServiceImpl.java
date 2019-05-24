package com.graduate.jrsmain.service.impl;
import com.graduate.jrsmain.bean.CustomUserDetails;
import com.graduate.jrsmain.bean.LawUser;
import com.graduate.jrsmain.bean.User;
import com.graduate.jrsmain.dao.UserMapper;
import com.graduate.jrsmain.service.UserService;
import com.graduate.jrsmain.util.LawException;
import com.graduate.jrsmain.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LawUser user = userMapper.getUserByUserName(s);
        return new CustomUserDetails(user);
    }

//    @Override
//    public User getUserByUserName(String s) {
//        return userMapper.getUserByUserName(s);
//    }
//
//    @Override
//    public LawUser getUserByUserNameExcludePassWord(String s) {
//        return userMapper.getUserByUserNameExcludePassWord(s);
//    }

    @Override
    public String register(LawUser user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        int i = userMapper.addUser(user);
        if(i==1){
            return "注册成功";
        }else {
            throw  new LawException(ResultCode.REGISTER_ERROR);
        }
    }
}
