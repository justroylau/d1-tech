package com.liang.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liang.tech.VO.ResultVO;
import com.liang.tech.service.ArticleClassService;
import com.liang.tech.util.ResultVOUtil;

@RestController
public class ArticleClassController {
	
	
	@Autowired
	private ArticleClassService articleClassService;
	 
   	@GetMapping("classArray")
   	public ResultVO classArray() {
		return ResultVOUtil.success(articleClassService.findClassArray());
	}
}
