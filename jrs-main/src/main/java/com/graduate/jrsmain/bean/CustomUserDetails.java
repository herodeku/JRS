package com.graduate.jrsmain.bean;

import java.util.Collections;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User{

    private LawUser user;

    public CustomUserDetails(LawUser user) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, Collections.EMPTY_SET);
        this.user = user;
    }

    public LawUser getUser() {
        return user;
    }

    public void setUser(LawUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "user=" + user +
                '}';
    }
}
