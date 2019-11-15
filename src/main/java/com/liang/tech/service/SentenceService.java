package com.liang.tech.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.liang.tech.mapper.SentenceMapper;
import com.liang.tech.pojo.Sentence;



@Service
public class SentenceService {
	
	@Autowired
	private SentenceMapper sentenceMapper;
	
	public Sentence selectSentence() {
		
		return sentenceMapper.selectSentence();
		
	}
}
