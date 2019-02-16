package com.authstr.ff.utils.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.exception.AuthstrException;
import com.authstr.ff.utils.exception.MsgException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
@Component
public class AbstractController {
	protected Logger log = LogManager.getLogger(this.getClass());
	@ExceptionHandler(value={Exception.class})
    @ResponseBody
    public Map exceptionHandler(Exception ex) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        if (ex instanceof MsgException) {//出现普通异常
        	MsgException msg=(MsgException) ex;
        	if(StringUtils.hasText(msg.getCode())){//如果异常里存在code信息
        		  model.put(ControllerConstant.CODE, msg.getCode());
        	}else{
        		  model.put(ControllerConstant.CODE, ControllerConstant.SYSTEM_ERROR);//没有则设置为-1
        	}
            model.put(ControllerConstant.MSG, ex.getMessage());//设置消息
            if(msg.getData()!=null)model.put(ControllerConstant.DATA, msg.getData());//如果有,设置相关数据
            
        } else if(ex instanceof AuthstrException){//出现内部异常(内部提示,不显示到前端)
        	  model.put(ControllerConstant.CODE, ControllerConstant.SYSTEM_ERROR);//设置为-1
        	  model.put(ControllerConstant.MSG, ControllerConstant.ERROR_MSG);
        	  log.error("系统出现异常:" + ex.getMessage());
        }
//        else if (ex instanceof CapecAPIException) {
//            model.put(APIModel.CODE, ex.getMessage());
//        } else if (ex instanceof ConstraintViolationException) {
//            model.put(APIModel.CODE, APIModel.SYSTEM_ERROR);
//            Set ess = ((ConstraintViolationException)ex).getConstraintViolations();
//            model.put(APIModel.MSG, ess.iterator().next());
//        } 
        else {
            model.put(ControllerConstant.CODE, ControllerConstant.SYSTEM_ERROR);
            this.log.error( ControllerConstant.ERROR_MSG+":" + ex.getMessage());
        }
        ex.printStackTrace();
        return model;
    }

	    public Map success() {
	        HashMap<String, Integer> map = new HashMap<String, Integer>();
	        map.put(ControllerConstant.CODE, ControllerConstant.SYSTEM_SUCCESS);
	        return map;
	    }
}
