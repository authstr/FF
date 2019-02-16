package com.authstr.ff.utils.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice	//声明控制器增强组件,该类将作用在所有注解了@RequestMapping的控制器的方法上。
public class CatchException {
        @ExceptionHandler(value=BindException.class)//数据绑定异常
		public String catchBindException(BindException e,HttpServletRequest request){
        	//用于处理Spring框架封装Bean出现的封装错误
        	//处理方式是跳转到上一个请求路径,并将错误信息转发过去
			String objectName=e.getObjectName();//出错的对象类名称
			FieldError errorAttr= e.getFieldError();//出错的属性对象
			String attrName=null;//出错的属性名称
			String attrValue=null;//出错的属性值(应为字符串)
			String attrType=null;//出错的属性类型
			if(errorAttr!=null){//如果属性对象不为空
				 attrName=errorAttr.getField();
				 attrValue=errorAttr.getRejectedValue().toString();
				 attrType=e.getFieldType(errorAttr.getField()).toString();
			}else{
				 attrName="获取属性名出错";
				 attrValue="获取属性值出错";
				 attrType="获取属性类型失败";
			}
						
		
		
			
			//将异常信息发给上一个页面
			String path=getLastPath(request);
			
			//获取错误的提示信息
			//bean封装类抛出的异常被嵌套在BindException异常中,需要剪切字符串
			//字符串如:nested exception is java.lang.IllegalArgumentException: 出现异常]
			//分割"\n",取第二行,防止有多个错误;分割nested exception is,取出嵌套的异常信息;
			//分割Exception:把异常名字去除,获取getMessage的信息;替换后面的],完成
			String mess=e.getMessage().split("\n")[1].split("nested exception is")[1].split("Exception:")[1].replace("]", "");
			//拼接错误信息
			String errorMsg=""
					+"["+mess+"]"
					+ "无法将值\""+attrValue+"\"储存在\""
					+objectName+"\"类下的\""
					+attrName+"\"属性,"
					+"因为该属性的类型为\""+attrType+"\""
					+"即將跳转到上一个地址:\""+path
					+"\"请确认该路径!!!!!";
			request.setAttribute("alert", errorMsg);//设置错误信息
			System.out.println(errorMsg);
			//将其他错误打印出来
			if(e.getErrorCount()>1){
//				System.out.println(e.getMessage());
				System.out.println("共"+e.getErrorCount()+"个参数非法");
			}
			
			
//			System.out.println(path);
			return path;
		}
        
        @ExceptionHandler(value= java.lang.NullPointerException.class)//数据绑定异常
		public String catchNullPointException(NullPointerException e,HttpServletRequest request){
        	//将异常信息发给上一个页面
			String path=getLastPath(request);
			String errorMsg="发生了空指针异常,请在控制台查看! 可能是未登录,或者是程序员太菜,或者是程序员太菜!"+"   即將跳转到上一个地址:\""+path
					+"\"请确认该路径!!!!!";;
			request.setAttribute("alert", errorMsg);//设置错误信息
			System.out.println(errorMsg);
        	return path;
        }
        @ExceptionHandler(value= Exception.class)//数据绑定异常
		public String catchException(Exception e,HttpServletRequest request){
        	//将异常信息发给上一个页面
			String path=getLastPath(request);
			String errorMsg="发生了异常,请在控制台查看! 简述:\""+e.getMessage()+"\""+"产生原因:程序员太菜.  "+"   即將跳转到上一个地址:\""+path
					+"\"请确认该路径!!!!!";;
			request.setAttribute("alert", errorMsg);//设置错误信息
			System.out.println(errorMsg);
        	return path;
        }
        //获取请求的上一个路径
        public String getLastPath(HttpServletRequest request){
        	String path="/error.jsp";//要跳转的路径
			String referer=request.getHeader("referer");//获取上一个地址
			if(referer!=null){
				String[] servletPath=referer.split("/");//将上一个地址根据/分割
				/*分割成诸如这样的格式
				 	* http:
				    *
				    *localhost:8080
				    *ProjectTemplate
				    *template.jsp
				  */
				String temp="";//用于存储拼接的路径
				for(int i=4;i<servletPath.length;i++){//遍历拼接,从第4个开始,不需要包含项目名
					temp=temp+"/"+servletPath[i];
	            }
				path=temp;
			}
			return path;
        }
}
