package com.liang.tech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourcesConfiguration extends WebMvcConfigurerAdapter{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径
		
		//win
		//registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:C:/eclipseWorkspace/uploadFile/");
		
		//linux
		registry.addResourceHandler("/uploadFile/**").addResourceLocations("file:/uploadFile/");
		
		super.addResourceHandlers(registry);
	}
	
}
