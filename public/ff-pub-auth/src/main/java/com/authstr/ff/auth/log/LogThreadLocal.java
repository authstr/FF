package com.authstr.ff.auth.log;

import com.authstr.ff.model.platform.base.BaseBusinessLog;
import com.authstr.ff.utils.login.LoginInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 储存业务日志对象的线程
 * @author authstr
 */
public class LogThreadLocal {
	 private static Log log = LogFactory.getLog(LogThreadLocal.class);
	 private static ThreadLocal<BaseBusinessLog> THREAD = new ThreadLocal();
	 public static void set(BaseBusinessLog loginfo) {
		if (THREAD.get() != null && loginfo != null) {
			if ( !(THREAD.get() instanceof BaseBusinessLog )) {
				log.error((Object)"LogThreadLocal 进行set出错,存在未知对象");
			}
			THREAD.set(null);
			THREAD.remove();
		}
		THREAD.set(loginfo);
	 }

	    public static BaseBusinessLog get() {
	        return THREAD.get();
	    }

	    public static void remove() {
	        THREAD.set(null);
	        THREAD.remove();
	    }
}
