package com.authstr.ff.auth.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 用于从配置文件拿值
 * @time 2018年10月12日16:26:18
 * @author authstr
 */
@Component("interceptorConfigVar")
public class InterceptorConfigVar {
	@Value("${authstr.interceptor.login.url:}")
	public String LOGIN_URL;
	@Value("${authstr.interceptor.logout.url:}")
	public String LOGOUT_URL;
}
