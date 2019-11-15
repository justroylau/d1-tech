package com.liang.tech.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.liang.tech.VO.ResultVO;
import com.liang.tech.pojo.Articles;
import com.liang.tech.pojo.ConditionInfo;
import com.liang.tech.service.ArticlesService;
import com.liang.tech.util.ResultVOUtil;

@RestController
public class ArticlesController {
	
	
	@Autowired
	private ArticlesService articlesService;
	 
   	@PostMapping("findByArticleId")
	public ResultVO findByArticleId(HttpServletRequest request,Integer id) {
   		return articlesService.findByArticleId(request,id);
	}
   	
   	@PostMapping("findByLike")
   	public ResultVO findByLike(HttpServletRequest request,Integer pageNum,Integer pageSize,ConditionInfo conditionInfo) {
   		
   		PageInfo pageInfo = articlesService.findArticleLike(request,pageNum, pageSize, conditionInfo);
		return ResultVOUtil.success(pageInfo);
	}
   	
   	@PostMapping("addSee")
   	public ResultVO addSee(Integer id,HttpServletRequest request) {
   		Integer seeState = articlesService.seeAdd(id, request);
   		if(seeState == 0) {
   			return ResultVOUtil.success();
   		}else {
   			return ResultVOUtil.error(-2, "不能刷查看数");
   		}
   	}
   	
	@PostMapping("insertArticle")
	public ResultVO insertArticle(Articles articles,HttpServletRequest request) {
		return articlesService.insertArticles(articles,request);
	}
   	
	@PostMapping("updateArticle")
	public ResultVO updateArticle(Articles articles,HttpServletRequest request) {
		return articlesService.updateByPrimaryKeySelective(articles,request);
	}
	
	@PostMapping("deleteArticle")
	public ResultVO deleteByPrimaryKey(Integer id,HttpServletRequest request) {
		return articlesService.deleteByPrimaryKey(id,request);
	}
   	
}
