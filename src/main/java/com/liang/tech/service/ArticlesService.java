package com.liang.tech.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liang.tech.VO.ResultVO;
import com.liang.tech.enums.ResultEnum;
import com.liang.tech.mapper.ArticlesMapper;
import com.liang.tech.pojo.Articles;
import com.liang.tech.pojo.ArticlesCustom;
import com.liang.tech.pojo.ConditionInfo;
import com.liang.tech.pojo.Users;
import com.liang.tech.util.DateUtil;
import com.liang.tech.util.ResultVOUtil;



@Service
public class ArticlesService {
	
	@Autowired
	private ArticlesMapper articlesMapper;
	
	/**
	 * 模糊查询文章列表
	 * @param pageNum
	 * @param pageSize
	 * @param calssid
	 * @return
	 */
	public PageInfo findArticleLike(HttpServletRequest request,Integer pageNum,Integer pageSize,ConditionInfo conditionInfo){
		Users user = (Users) request.getSession().getAttribute("techUsers");
		conditionInfo.setIsadmin(null);
		if(user != null ) {
			if(user.getRoles().equals("admin") || user.getRoles().equals("super"))  {
				conditionInfo.setIsadmin("admin");
			}
		}
		if(pageNum != null && pageSize !=null){
			System.out.println("分页内容1:"+pageNum+"----------"+pageSize);
			PageHelper.startPage(pageNum,pageSize);
		}
		
		List<Articles> osList = articlesMapper.findArticleLike(conditionInfo);
		return new PageInfo(osList);
	}
	
	/**
	 * 查询文章详情
	 * @param id
	 * @return
	 */
	public ResultVO findByArticleId(HttpServletRequest request,Integer id){
		ArticlesCustom articlesCustom = articlesMapper.selectByPrimaryKey(id);
		//System.out.println("是否删除"+articlesCustom.getIsdelete());
		if(articlesCustom.getIsdelete().equals(0)) {
			return ResultVOUtil.success(articlesCustom);
		}else {
			return ResultVOUtil.error(ResultEnum.ARTICLE_NULL.getCode(), ResultEnum.ARTICLE_NULL.getMessage());
		}
	}
	
	/**
	 * 添加文章
	 * @param articles
	 * @return
	 */
	public ResultVO insertArticles(Articles articles,HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("techUsers");
		if(user != null) {
			articles.setUserid(user.getId());
			articles.setCreatetime(DateUtil.getSecondTimestamp());
			articles.setUpdatetime(DateUtil.getSecondTimestamp());;
			return ResultVOUtil.success(articlesMapper.insertSelective(articles));
		}else {
			return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage());
		}
	}
	/**
	 * 浏览数+1
	 * @param id
	 */
	public Integer seeAdd(Integer id,HttpServletRequest request) {
		
		/*System.out.println("session:"+request.getSession().getAttribute("seeBool"+id));
   		if(request.getSession().getAttribute("seeBool"+id) == null) {
   			request.getSession().setAttribute("seeBool"+id,"true");//这是jsp文件，如果是Servlet的话，先得获得Session，HttpSession hs=request.getSession(true);
   	   		request.getSession().setMaxInactiveInterval(5);
   	   		articlesMapper.seeAdd(id);
   	   		return 0;
   		}else {
   			return -1;
   		}
   		*/
   		
   		articlesMapper.seeAdd(id);
   		return 0;
	}
	
	/**
	 * 修改文章
	 * @return
	 */
	public ResultVO updateByPrimaryKeySelective(Articles articles,HttpServletRequest request) {
		Users users = (Users)request.getSession().getAttribute("techUsers");
		if(users != null) {
			Articles articlesTemp = articlesMapper.selectByPrimaryKey(articles.getId());
			//System.out.println(users.getId()+"-----"+articlesTemp.getUserid());
			if(articlesTemp.getUserid().equals(users.getId()) || users.getRoles().equals("admin") || users.getRoles().equals("super")) {
				articles.setCreatetime(null);
				articles.setIsdelete(null);
				articles.setIstop(null);
				articles.setUpdatetime(DateUtil.getSecondTimestamp());
				articles.setUserid(null);
				return ResultVOUtil.success(articlesMapper.updateByPrimaryKeySelective(articles));
			}else {
				return ResultVOUtil.error(ResultEnum.USER_NOT_ROLE.getCode(), ResultEnum.USER_NOT_ROLE.getMessage());
			}
		}else {
			return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage());
		}
		
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @param request
	 * @return
	 */
	public ResultVO deleteByPrimaryKey(Integer id,HttpServletRequest request) {
		Users users = (Users)request.getSession().getAttribute("techUsers");
		if(users != null) {
			Articles articlesTemp = articlesMapper.selectByPrimaryKey(id);
			if(articlesTemp.getUserid().equals(users.getId()) || users.getRoles().equals("admin") || users.getRoles().equals("super")) {
				return ResultVOUtil.success(articlesMapper.deleteByPrimaryKey(id));
			}else {
				return ResultVOUtil.error(ResultEnum.USER_NOT_ROLE.getCode(), ResultEnum.USER_NOT_ROLE.getMessage());
			}
		}else {
			return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage());
		}
	}
	
	
	
}
