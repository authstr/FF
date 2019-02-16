package com.authstr.ff.utils.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 登录状态的线程
 * @author authstr
 */
public class LoginThreadLocal {
	 private static Log log = LogFactory.getLog(LoginThreadLocal.class);
	    private static ThreadLocal<LoginInfo> THREAD = new ThreadLocal();

	    public static void set(LoginInfo school) {
	        if (THREAD.get() != null && school != null) {
	            if (THREAD.get() instanceof LoginInfo) {
	                log.error((Object)("对LoginThreadLocal 进行set出错,已存在LoginInfo对象"));
	            } else {
	                log.error((Object)"对LoginThreadLocal 进行set出错,存在未知对象");
	            }
	            THREAD.set(null);
	            THREAD.remove();
	        }
	        THREAD.set(school);
	    }

	    public static LoginInfo get() {
	        return THREAD.get();
	    }

	    public static void remove() {
	        THREAD.set(null);
	        THREAD.remove();
	    }
}
