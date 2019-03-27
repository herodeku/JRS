package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.LawUser;

import java.util.List;

public interface TopSearchService {
    List<Object> recommend(LawUser user);
}
