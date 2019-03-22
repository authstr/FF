package com.authstr.ff.utils.exception;

/**
 * 错误型异常,用来记录系统内部未知的出错
 * @time 2018年9月26日10:53:05
 * @author authstr
 */
public class ErrorException extends BasicException {
	public ErrorException() {
		super();
	}

	public ErrorException(String message) {
		super(message);
		this.code=DEFAULT_CODE;
	}
	
	public ErrorException(String code, String message){
		this(message);
		this.code=code;
	}
	public ErrorException(String code, String message, Object data){
		this(message);
		this.code=code;
		this.data=data;
	}
}
