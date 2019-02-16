package com.authstr.ff.utils.http;

import javax.servlet.http.HttpSession;


public class SessionUtil {
	
	
	/**
	 * session中是否存在指定Key的对象
	 * @param session
	 * @param sessionKey
	 * @return
	 * @time 2018年10月13日21:24:32
	 * @author authstr
	 */
	public static boolean hasSession(HttpSession session,String sessionKey){
		return session!=null&&session.getAttribute(sessionKey) != null; 
	}
	
	
	
	/**
	 * 获取session中指定Key的值
	 * @param session
	 * @param sessionKey
	 * @return
	 * @time 2018年10月13日21:36:03
	 * @author authstr
	 */
	public static Object getSession(HttpSession session,String sessionKey){
		if(session==null)return null;
		return session.getAttribute(sessionKey);
	}
	

	
	/**
	 * 移除session中指定key的值
	 * @param session
	 * @param sessionKey
	 * @time 2018年10月13日21:38:52
	 * @author authstr
	 */
	public static void removeSession(HttpSession session,String sessionKey){
		session.removeAttribute(sessionKey);
	}
	

}
