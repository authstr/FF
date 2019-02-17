package com.authstr.sd.basic.utils;

public enum LoginInfoEnum  {
	/**
     * 用户名、密码为空
     */
	Name_Or_Pwd_Is_Null("-1","用户或密码为空"),
	/***
	 * 验证码错误
	 */
	The_VerfiCode_Is_Wrong("-2","验证码错误"),
	/***
	 * 用户名或密码错误
	 */
	Name_Or_Pwd_Is_Wrong("-3","用户名或密码错误"),
	/***
	 *用户不可用
	 */
	User_Is_Enable("-4","用户名不可用"),
	/***
	 * 登陆时出现未知错误
	 */
	Login_Unknow_Error("-200","登陆时出现未知错误");
	
	private String code;
	private String explain;
	private LoginInfoEnum(String code,String explain){
		this.code=code;
		this.explain=explain;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
