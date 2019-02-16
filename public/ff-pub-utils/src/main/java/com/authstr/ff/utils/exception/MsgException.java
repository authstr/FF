package com.authstr.ff.utils.exception;

import java.util.Map;

/**
 * 用于传递异常信息
 * @time 2018年9月26日10:52:51
 * @author authstr
 *
 */
public class MsgException extends RuntimeException {
	private String code;
	private Object data;
	public MsgException() {
		super();
	}

	public MsgException(String message) {//定义错误信息,错误代码默认-1
		super(message);
		code="-1";
	}
	
	public MsgException(String code,String message){//自定义错误代码和错误信息
		this(message);
		this.code=code;
	}
	public MsgException(String code,String message,Object data){
		this(message);
		this.code=code;
		this.data=data;
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
