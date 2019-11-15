package com.liang.tech.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.tech.VO.ResultVO;
import com.liang.tech.enums.ResultEnum;
import com.liang.tech.mapper.UsersMapper;
import com.liang.tech.pojo.ArticlesCustom;
import com.liang.tech.pojo.Users;
import com.liang.tech.util.DateUtil;
import com.liang.tech.util.MD5Util;
import com.liang.tech.util.ResultVOUtil;



@Service
public class UserService {
	
	@Autowired
	private UsersMapper usersMapper;
	
	
	/**
	 * 登陆
	 * @param users
	 * @param request
	 * @return
	 */
	public ResultVO findNameAndPwd(Users users,HttpServletRequest request) {
		Users userTemp = usersMapper.findNameAndPwd(users);
		if(userTemp != null) {
			boolean islogin = MD5Util.verify(users.getUpwd(),userTemp.getUpwd());
			if(islogin) {
	   			request.getSession().setAttribute("techUsers", userTemp);
	   			request.getSession().setMaxInactiveInterval(3600);//60分钟不操作
	   			
	   			Users logginUsers = new Users();
	   			logginUsers.setId(userTemp.getId());
	   			logginUsers.setLastlogintime(DateUtil.getSecondTimestamp());//写入最后登陆时间
	   			usersMapper.updateByPrimaryKeySelective(logginUsers);
	   			
	   			return ResultVOUtil.success(userTemp);
	   		}else {
	   			return ResultVOUtil.error(ResultEnum.USER_PWD_ERROR.getCode(), ResultEnum.USER_PWD_ERROR.getMessage());
	   		}
		}else {
			return ResultVOUtil.error(ResultEnum.USER_NULL.getCode(), ResultEnum.USER_NULL.getMessage());
		}
	}
	
	/**
	 * 注册
	 * @param users
	 * @param request
	 * @return
	 */
	public ResultVO insertSelective(Users users,HttpServletRequest request){
		Users userSession = (Users)request.getSession().getAttribute("techUsers");
		if(userSession!=null) {
			if(userSession.getRoles().equals("super") || userSession.getRoles().equals("admin")) {
				Users userTemp = usersMapper.findNameAndPwd(users);
				if(userTemp == null) {
					users.setCreatetime(DateUtil.getSecondTimestamp());
					users.setAlias("用户"+DateUtil.getSecondTimestamp());
					users.setRoles("ordinary");
					users.setUpwd(MD5Util.generate(users.getUpwd()));
					int result = usersMapper.insertSelective(users);
					return ResultVOUtil.success(result);
				}else {
					return ResultVOUtil.error(ResultEnum.USER_NOT_NULL.getCode(), ResultEnum.USER_NOT_NULL.getMessage());
				}
			}else {
				return ResultVOUtil.error(ResultEnum.USER_NOT_ROLE.getCode(), ResultEnum.USER_NOT_ROLE.getMessage());
			}
		}else {
			return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage());
		}
	}
	
	/**
	 * 修改密码
	 * @param users
	 * @param request
	 * @return
	 */
	public ResultVO updatepwd(Users users,HttpServletRequest request){
		Users userSession = (Users)request.getSession().getAttribute("techUsers");
		if(userSession!=null) {
			Users userTemp = usersMapper.findNameAndPwd(users);
			if(userTemp!=null) {
				if(userSession.getId().equals(userTemp.getId()) || userSession.getRoles().equals("admin") || userSession.getRoles().equals("super")) {
					users.setUname(null);
					users.setRoles(userTemp.getRoles());
					users.setUpwd(MD5Util.generate(users.getUpwd()));
					users.setId(userTemp.getId());
					System.out.println("修改密码:"+users.toString());
					return ResultVOUtil.success(usersMapper.updateByPrimaryKeySelective(users));
				}else {
					return ResultVOUtil.error(ResultEnum.USER_NOT_ROLE.getCode(), ResultEnum.USER_NOT_ROLE.getMessage());
				}
			}else {
				return ResultVOUtil.error(ResultEnum.USER_NULL.getCode(), ResultEnum.USER_NULL.getMessage());
			}
		}else {
			return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage());
		}
	}
	/**
	 * 修改个人资料
	 * @param users
	 * @param request
	 * @return
	 */
	public ResultVO updateUser(Users users,HttpServletRequest request){
		Users userSession = (Users)request.getSession().getAttribute("techUsers");
		if(userSession!=null) {
			users.setUname(null);
			users.setRoles(null);
			users.setUpwd(null);
			users.setId(userSession.getId());
			System.out.println("修改资料:"+users.toString());
			request.getSession().setAttribute("techUsers", users);
   			request.getSession().setMaxInactiveInterval(3600);//60分钟不操作
			return ResultVOUtil.success(usersMapper.updateByPrimaryKeySelective(users));
		}else {
			return ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage());
		}
	}
	
}
