package com.freebo.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserInfo {

	/**
	 * 编号
	 */
	private Integer userId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String passWord;
	
	/**
	 * 生日
	 */
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;
	
	/**
	 * 电话号码
	 */
	private String telNum;
	
	public UserInfo() {
		super();
	}

	/**
	 * 构造方法
	 * @param userId
	 * @param userName
	 * @param passWord
	 * @param birthday
	 * @param telNum
	 */
	public UserInfo(Integer userId, String userName, 
			String passWord, Date birthday, String telNum) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.birthday = birthday;
		this.telNum = telNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", birthday="
				+ birthday + ", telNum=" + telNum + "]";
	}
	
}
