package com.liang.tech.mapper;

import java.util.List;

import com.liang.tech.pojo.ArticleClass;

public interface ArticleClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleClass record);

    int insertSelective(ArticleClass record);

    ArticleClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleClass record);

    int updateByPrimaryKey(ArticleClass record);
    
    //
    List<ArticleClass> selectBylist();
}