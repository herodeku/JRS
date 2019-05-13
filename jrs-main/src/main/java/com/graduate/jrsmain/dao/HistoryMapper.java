package com.graduate.jrsmain.dao;

import com.graduate.jrsmain.bean.History;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryMapper {

    @Insert("INSERT INTO HISTORY VALUES(#{id},#{indexId},#{username},#{historyFrom},#{triggerTime})")
    public int addHistory(History history);

    @Delete("DELETE FROM HISTORY WHERE username=#{userName}")
    public int deleteHistory(String userName);

    @Select("SELECT * FROM HISTORY WHERE username=#{userName}")
    public List<History> getHistory(String userName);

}
