package com.authstr.ff.utils.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.exception.ErrorException;
import com.authstr.ff.utils.exception.MsgException;


/**
 * 控制层的超类,
 * 主要对控制层的异常进行捕获,通过接口返回或者进行其他处理
 * @author authstr
 * 2019年7月5日21:21:36
 */
@Component
public class AbstractController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value={Exception.class})
    @ResponseBody
	//@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
	public Map exceptionHandler(Exception ex) {
		//异常处理前,进行一次调用,具体实现应由子类实现
		exceptionHandlerBefore(ex);
        HashMap<String, Object> model = new HashMap<String, Object>();
		//如果是消息型异常
        if (ex instanceof MsgException) {
        	MsgException msg=(MsgException) ex;

			//尝试从异常获取错误代码,没有则使用默认的错误代码
        	if(StringUtils.hasText(msg.getCode())){
        		  model.put(ControllerConstant.CODE, msg.getCode());
        	}else{
        		  model.put(ControllerConstant.CODE,MsgEnum.UNKNOWN_ERROR.getCode());
        	}

			//尝试从异常获取错误说明,没有则使用默认的错误说明
			if(StringUtils.hasText(msg.getMessage())){
				model.put(ControllerConstant.MSG, msg.getMessage());
			}else{
				model.put(ControllerConstant.MSG,MsgEnum.UNKNOWN_ERROR.getMessage());
			}

			//获取异常详细数据,如果有,进行返回
            if(msg.getData()!=null){
             	model.put(ControllerConstant.DATA, msg.getData());
            }

            
        } else if(ex instanceof ErrorException){
        	//出现错误型异常,在返回值显示未知异常,具体错误消息进行记录和打印
			model.put(ControllerConstant.CODE,MsgEnum.UNKNOWN_ERROR.getCode());
			model.put(ControllerConstant.MSG,MsgEnum.UNKNOWN_ERROR.getMessage());
			ex.printStackTrace();
			log.error("系统执行出现异常:" + ex.getMessage());
        }
        else {
			model.put(ControllerConstant.CODE,MsgEnum.UNKNOWN_ERROR.getCode());
			model.put(ControllerConstant.MSG,MsgEnum.UNKNOWN_ERROR.getMessage());
			ex.printStackTrace();
            this.log.error( "系统出现未知异常 :" + ex.getMessage());
        }
		//异常处理后,进行一次调用,具体实现应由子类实现
		exceptionHandlerAfter(ex,model);
        return model;
    }

	public Map success() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(ControllerConstant.CODE,MsgEnum.SUCCESS.getCode());
		map.put(ControllerConstant.MSG,MsgEnum.SUCCESS.getMessage());
		return map;
	}

	public  void exceptionHandlerBefore(Exception e){};

	public  void exceptionHandlerAfter(Exception e,Map info){};


}
