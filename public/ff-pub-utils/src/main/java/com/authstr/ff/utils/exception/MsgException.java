package com.authstr.ff.utils.exception;

import java.util.Map;

/**
 * 消息型异常,用来在接口返回值显示消息
 * @time 2018年9月26日10:52:51
 * @author authstr
 *
 */
public class MsgException extends BasicException {
	public MsgException() {
		super();
	}

	public MsgException(String message) {
		super(message);
		code=DEFAULT_CODE;
	}
	
	public MsgException(String code,String message){
		this(message);
		this.code=code;
	}
	public MsgException(String code,String message,Object data){
		this(message);
		this.code=code;
		this.data=data;
	}
}
