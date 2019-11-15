package com.liang.tech.config;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

public class TemplateFile {
	
	/** * 文件上传临时路径 */
	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation("/tmp/uploadTmp");
		return factory.createMultipartConfig();
	}

}
