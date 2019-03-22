package com.authstr.ff.utils.exception;

import com.authstr.ff.utils.web.controller.MsgEnumInterface;

/**
 * 所有自定义异常的父类
 * @time 2018年9月26日10:53:05
 * @author authstr
 */
public class BasicException extends RuntimeException {

	public final static  String DEFAULT_CODE="-1";
	String code;
	Object data;
	public BasicException() {
		super();
	}

	public BasicException(String message) {
		super(message);
		code=DEFAULT_CODE;
	}

	public BasicException(String code, String message){
		this(message);
		this.code=code;
	}
	public BasicException(String code, String message, Object data){
		this(message);
		this.code=code;
		this.data=data;
	}

	//通过消息枚举创建异常
	public BasicException(MsgEnumInterface msgEnum){
		this(msgEnum.getMessage());
		this.code=msgEnum.getCode();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
