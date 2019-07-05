package com.authstr.ff.basic;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * servle的启动类,在tomcat启动时,启动spring boot
 * @tame 2018年10月11日10:07:15
 * @author authstr
 */
public class ServletInit extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(ApplicationInit.class);
	}
	
}
