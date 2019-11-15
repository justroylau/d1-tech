package com.liang.tech.enums;

public enum ResultEnum {
	
	USER_NULL(10,"用户不存在"),
	USER_PWD_ERROR(11,"密码错误"),
	USER_NOT_LOGIN(12,"请先登陆"),
	USER_NOT_ROLE(13,"权限不足"),
	USER_NOT_NULL(14,"用户已存在"),
	
	ARTICLE_NULL(30,"文章不存在"),
	
	USER_LOCK(16,"用户处于锁住状态"),
	RE_PWD_ERROR(17,"旧密码有误"),
	OPERATION_FAST(18,"code重复，操作太快"),
	;
	
	
	private Integer code;
	private String message;
	
	private ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	
	
}
