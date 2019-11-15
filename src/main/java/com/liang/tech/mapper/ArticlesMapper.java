package com.liang.tech.mapper;

import java.util.List;
import com.liang.tech.pojo.Articles;
import com.liang.tech.pojo.ArticlesCustom;
import com.liang.tech.pojo.ConditionInfo;

public interface ArticlesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Articles record);

    int insertSelective(Articles record);

    ArticlesCustom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Articles record);

    int updateByPrimaryKeyWithBLOBs(Articles record);

    int updateByPrimaryKey(Articles record);
    
    //
    List<Articles> findArticleLike(ConditionInfo conditionInfo);
    
    void seeAdd(Integer id);
}