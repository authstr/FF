package com.authstr.ff.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * spring的初始化类,用于启动spring boot
 * @time 2018年10月11日10:03:44
 * @author authstr
 */
@SpringBootApplication(scanBasePackages={"com.authstr.*"})
@EnableTransactionManagement//开启事务管理
@EntityScan(basePackages={"com.authstr.*"})
public class ApplicationInit {
	
	public static void main(String[] args) {
		System.out.println("login");
		SpringApplication.run(ApplicationInit.class, args);
	}
}
