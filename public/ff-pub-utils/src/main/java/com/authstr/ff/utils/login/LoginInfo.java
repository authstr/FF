package com.authstr.ff.utils.login;

import java.io.Serializable;
import java.util.Map;

/**
 * 储存登录信息
 * @author authstr
 */
public class LoginInfo {
	private  String userID;
	private  String username;
	private  Map<String,Object> Info;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Map<String, Object> getInfo() {
		return Info;
	}
	public void setInfo(Map<String, Object> info) {
		Info = info;
	}

}
