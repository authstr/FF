package com.authstr.ff.auth.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.authstr.ff.utils.base.StringUtils;

/**
 * 拦截器配置类,用于注册拦截器
 * @time 2018年10月12日17:12:04
 * @author authstr
 */
@Component 
public class InterceptorConfiguration extends WebMvcConfigurerAdapter  {
	//要排除的url,定义在login模块的配置文件里
	@Value("${authstr.login.interceptor.exclude:}")
	private String exclude;
	
	//要排除的日志
//	@Value("${authstr.log.interceptor.exclude:}")
//	private String logexclude;
	
	//模块代码
	@Value("${authstr.default.systemcode:}")
	public String systemcode;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		  // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new AuthInterceptor());
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        if(StringUtils.hasText(exclude)){
        	String[] exs =exclude.split(",");
        	for(String e :exs){
        		if(!StringUtils.hasText(e))continue;
        		ir.excludePathPatterns(e);
        	}
        }
	}
	
}
