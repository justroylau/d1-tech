package com.liang.tech.mapper;

import com.liang.tech.pojo.Sentence;

public interface SentenceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sentence record);

    int insertSelective(Sentence record);

    Sentence selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sentence record);

    int updateByPrimaryKey(Sentence record);
    
    //
    Sentence selectSentence();
}