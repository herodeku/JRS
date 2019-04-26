package com.graduate.jrsmain.service;

import com.graduate.jrsmain.bean.History;
import java.util.List;

public interface HistoryService {
    public void addHistory(History history);
    public void deleteHistory(String userName);
    public List<Object> getHistory(String userName);
}
