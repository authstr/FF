package com.authstr.ff.auth.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志工具类
 * @time 2018年10月13日20:56:10
 * @author authstr
 */
public class LogUtils {
	private static Logger log = LogManager.getLogger(LogUtils.class);

	public static void info(String str){
		log.info(str);
	}

	public static void debug(String str){
		log.debug(str);
	}

	public static void error(String str){
		log.error(str);
	}

	public static void warn(String str){
		log.warn(str);
	}
}
