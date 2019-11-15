package com.liang.tech.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.JSON;
import com.liang.tech.enums.ResultEnum;
import com.liang.tech.pojo.Users;
import com.liang.tech.util.ResultVOUtil;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{
	@Bean
    public InterfaceAuthCheckInterceptor getInterfaceAuthCheckInterceptor() {
        return new InterfaceAuthCheckInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getInterfaceAuthCheckInterceptor()).addPathPatterns("/insertArticle","/ueditor","/imgUpload","/updateArticle","/deleteArticle","/updatepwd","/insertSelective","/updateUser");
        // 如果interceptor中不注入redis或其他项目可以直接new，否则请使用上面这种方式
        super.addInterceptors(registry);
    }

    
    /**
     * 微服务间接口访问密钥验证
     * @author xiaochangwei
     *
     */
    class InterfaceAuthCheckInterceptor implements HandlerInterceptor {

    	
    	//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
        @Override
        public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
                throws Exception {
        	System.out.println("afterCompletion");
        }

        
        //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
        @Override
        public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                throws Exception {
        	System.out.println("postHandle");
        }

        
        //在请求处理之前进行调用（Controller方法调用之前
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
                throws Exception {
        	System.out.println("preHandle");
        	System.out.println(request.getSession().getAttribute("techUsers"));
        	Users users = (Users) request.getSession().getAttribute("techUsers");
            //Users users = (Users)request.getAttribute("users");
            //System.out.println(request.getAttribute("users"));
            //System.out.println(users);
            if (users == null) {
            	 response.setContentType("application/json;charset=utf-8");
                 response.getWriter().write(JSON.toJSONString(ResultVOUtil.error(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMessage())));
                return false;
            } else {
                // TODO 验证逻辑
                return true;
            }
        }

    }
}
