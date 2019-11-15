package com.liang.tech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liang.tech.VO.ResultVO;
import com.liang.tech.enums.ResultEnum;
import com.liang.tech.pojo.Articles;
import com.liang.tech.pojo.ConditionInfo;
import com.liang.tech.pojo.Users;
import com.liang.tech.service.ArticleClassService;
import com.liang.tech.service.ArticlesService;
import com.liang.tech.service.UserService;
import com.liang.tech.util.ResultVOUtil;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	 
   	@PostMapping("login")
	public ResultVO findNameAndPwd(Users users,HttpServletRequest request) {
   		return userService.findNameAndPwd(users,request);
	}
   	@GetMapping("isLogin")
   	public ResultVO isLogin(HttpServletRequest request){
   		System.out.println(request.getSession().getAttribute("techUsers"));
   		Users users = (Users)request.getSession().getAttribute("techUsers");
   		if(users !=null ){
   	   		return ResultVOUtil.success(users);
   		}else {
   			return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage());
   		}
   	}
   	
   	@GetMapping("Logout")
   	public ResultVO Logout(HttpServletRequest request){
   		System.out.println("登出");
   		request.getSession().removeAttribute("techUsers");
   	   	return ResultVOUtil.success();
   	}
   	
   	@PostMapping("updatepwd")
   	public ResultVO updatepwd(Users users,HttpServletRequest request){
   		return userService.updatepwd(users, request);
   	}
   	
   	@PostMapping("updateUser")
   	public ResultVO updateUser(Users users,HttpServletRequest request){
   		return userService.updateUser(users, request);
   	}
   	@PostMapping("insertSelective")
   	public ResultVO insertSelective(Users users,HttpServletRequest request){
   		return userService.insertSelective(users, request);
   	}
   	
   	
   	
}
