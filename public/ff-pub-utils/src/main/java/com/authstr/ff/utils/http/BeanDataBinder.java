package com.authstr.ff.utils.http;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;


//@ControllerAdvice		//声明控制器增强组件,该类将作用在所有注解了@RequestMapping的控制器的方法上。
public class BeanDataBinder {
	//用于定义从Web请求参数到JavaBean对象的数据绑定的部分过程。
	
	@InitBinder	//设置数据绑定对象,该对象用于将前台参数封装成JavaBean
	public static  void registerBind(WebDataBinder binder){
		//为Date类型的数据注册转换类,注册类为匿名内部类
		//如果要封装的属性是Date,就会调用该类,执行setAsText方法,调用toDate方法
		 binder.registerCustomEditor(Date.class,new PropertyEditorSupport(){
			 @Override
		      public void setAsText(String text) throws IllegalArgumentException {
				 setValue(toDate(text));
			 }
		 } );  
		 
		//为int类型的数据注册转换类,过程同上
		 binder.registerCustomEditor(int.class, new PropertiesEditor(){
			 @Override
		      public void setAsText(String text) throws IllegalArgumentException {
				 setValue(toInt(text));
			 }
		 });
		 
		//为double类型的数据注册转换类
		 binder.registerCustomEditor(double.class, new PropertiesEditor(){
			 @Override
		      public void setAsText(String text) throws IllegalArgumentException {
				 setValue(toDouble(text));
			 }
		 });
	}
	
	//定义Date类型的转换
	public static Date toDate(String text)throws IllegalArgumentException{
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
        try {
            date = format.parse(text);
        } catch (ParseException e) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = format.parse(text);
            } catch (ParseException e1) {
            	throw new IllegalArgumentException("非法日期值");
            }
        }
        return date;
	}
	
	//定义int类型的转换
	public static int toInt(String text)throws IllegalArgumentException{
		int value = 0;
		if (text == null || text.equals("")) {   
		  throw new IllegalArgumentException("数字值为空");
	     }    
		try {
			value=Integer.parseInt(text);
		} catch (Exception e) {
			 throw new IllegalArgumentException("非法数字值");
		}
        return value;
	}
	
	//定义double类型的转换
	public static double toDouble(String text)throws IllegalArgumentException{
		double value = 0;
		if (text == null || text.equals("")) {   
		  throw new IllegalArgumentException("数字值为空");
	     }    
		try {
			value=Double.parseDouble(text);
		} catch (Exception e) {
			 throw new IllegalArgumentException("非法数字值");
		}
        return value;
	}

}

