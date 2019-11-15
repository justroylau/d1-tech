package com.liang.tech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liang.tech.VO.ResultVO;
import com.liang.tech.service.SentenceService;
import com.liang.tech.util.ResultVOUtil;

@RestController
public class SentenceController {
	
	@Autowired
	private SentenceService sentenceService;
	 
   	@GetMapping("findSentence")
   	public ResultVO findSentence(HttpServletRequest request){
   	   		return ResultVOUtil.success(sentenceService.selectSentence());
   	}
   	
}
