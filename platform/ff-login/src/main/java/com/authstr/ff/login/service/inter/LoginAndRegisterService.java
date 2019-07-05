package com.authstr.ff.login.service.inter;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.web.sevice.InterfaceService;

public interface LoginAndRegisterService extends InterfaceService{

	BaseUser validate(String username, String password);

	/**
	 * 
	 * @param username
	 * @param pwd
	 * @param email
	 * @time 2018年11月2日 上午10:01:19
	 * @author authstr
	 * @return 
	 */
	String register(String username, String pwd, String email);

}
