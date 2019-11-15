package com.liang.tech.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.tech.mapper.ArticleClassMapper;
import com.liang.tech.pojo.ArticleClass;



@Service
public class ArticleClassService {
	
	@Autowired
	private ArticleClassMapper articleClassMapper;
	
	
	public List<ArticleClass> findClassArray(){
		
		return articleClassMapper.selectBylist();
	}
	
}
