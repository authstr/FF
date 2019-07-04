package com.authstr.ff.auth.interceptor;

import com.authstr.ff.model.platform.base.LogConstant;
import com.authstr.ff.model.platform.base.LoginConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器配置类,用于注册拦截器
 * @time 2018年10月12日17:12:04
 * @author authstr
 */
@Component 
public class InterceptorConfiguration extends WebMvcConfigurerAdapter  {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	//注入环境变量
	@Autowired
	private Environment env;



	//要排除的日志
//	@Value("${authstr.log.interceptor.exclude:}")
//	private String logexclude;
	
	//模块代码
	@Value("${authstr.default.systemcode:}")
	public String systemcode;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("dsadasdaa");
		setLoginInterceptor(registry);
		setLogInterceptor(registry);
//		// 注册拦截器
//        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
//        // 配置拦截的路径
//        ir.addPathPatterns("/**");
//        if(StringUtils.hasText(exclude)){
//        	String[] exs =exclude.split(",");
//        	for(String e :exs){
//        		if(!StringUtils.hasText(e))continue;
//        		ir.excludePathPatterns(e);
//        	}
//        }
	}

	//配置登录拦截器
	public void setLoginInterceptor(InterceptorRegistry registry){
		//获取是否要进行登录拦截
		Boolean isInterceptor=env.getProperty(LoginConstant.IS_LOGIN_INTERCEPTOR_KEY,Boolean.class,LoginConstant.IS_LOGIN_INTERCEPTOR_DEFAULT);
		if(isInterceptor){
			// 注册拦截器
			InterceptorRegistration Li = registry.addInterceptor(new LoginInterceptor());
			// 配置拦截的路径
			Li.addPathPatterns("/**");
			//获取不拦截的路径
			String exclude=env.getProperty(LoginConstant.LOGIN_INTERCEPTOR_EXCLUDE_KEY,LoginConstant.LOGIN_INTERCEPTOR_EXCLUDE_DEFAULT);
			//将String转换为list类型
			List<String> exclude_list= DefaultConversionService.getSharedInstance().convert(exclude,List.class);
			for(int i=0;i<exclude_list.size();i++){
				if(StringUtils.hasText(exclude_list.get(i))){
					Li.excludePathPatterns(exclude_list.get(i));
				}
			}

		}
	}

	//配置业务日志拦截器
	public void setLogInterceptor(InterceptorRegistry registry){
		//获取是否要进行登录拦截
		Boolean isInterceptor=env.getProperty(LogConstant.IS_LOG_INTERCEPTOR_KEY,Boolean.class,LogConstant.IS_LOG_INTERCEPTOR_DEFAULT);
		if(isInterceptor){
			// 注册拦截器
			InterceptorRegistration Li = registry.addInterceptor(new LogInterceptor());
			// 配置拦截的路径
			Li.addPathPatterns("/**");
			//获取不拦截的路径
			String exclude=env.getProperty(LogConstant.LOG_INTERCEPTOR_EXCLUDE_KEY,LogConstant.LOG_INTERCEPTOR_EXCLUDE_DEFAULT);
			//将String转换为list类型
			List<String> exclude_list= DefaultConversionService.getSharedInstance().convert(exclude,List.class);
			for(int i=0;i<exclude_list.size();i++){
				if(StringUtils.hasText(exclude_list.get(i))){
					Li.excludePathPatterns(exclude_list.get(i));
				}
			}

		}
	}

	
}
