package com.munendras.taskprojectlatest.payload;

public class UserCreateResponse {
	private Integer messagecode;
	private String status;
	private String message;
	private Object userInfo;
	
	
	public Integer getMessagecode() {
		return messagecode;
	}
	public void setMessagecode(Integer messagecode) {
		this.messagecode = messagecode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(Object userInfo) {
		this.userInfo = userInfo;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
