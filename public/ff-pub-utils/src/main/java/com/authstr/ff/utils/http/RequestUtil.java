package com.authstr.ff.utils.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.authstr.ff.utils.base.StringUtils;

/**
 * request的相关工具类
 * @author authstr
 *
 */
public class RequestUtil {
	
	//用于判断终端的形式
	public static String MOBILE = "mobile";//手机
    public static String WEB = "web";//网页
    public static String UNKNOW = "unknow";//未知
    public static String IOS = "ios";
    public static String ANDROID = "android";
    //用于字符匹配,判断终端形式
    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i|windows (phone|ce)|blackberry|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp|laystation portable)|nokia|fennec|htc[-_]|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    static Pattern phonePat = Pattern.compile(phoneReg, 2);
    static Pattern tablePat = Pattern.compile(tableReg, 2);

    /**
     * 判断终端是否为手机
     * @param request
     * @return
     * @time 2018年10月14日 下午5:44:31
     * @author authstr
     */
    public static boolean isMoblie(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        if (StringUtils.notText(userAgent))  return false;
        userAgent = userAgent.toLowerCase();
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        if (matcherPhone.find() || matcherTable.find()) return true;
        return false;
    }

    /**
     * 获取终端的形式
     * @param request
     * @return
     * @time 2018年10月14日 下午5:45:47
     * @author authstr
     */
    public static String getEndPointType(HttpServletRequest request) {
        return RequestUtil.isMoblie(request) ? MOBILE : WEB;
    }

    /**
     * 获取终端的形式(包括系统)
     * @param request
     * @return
     * @time 2018年10月14日 下午5:46:21
     * @author authstr
     */
    public static String getEndPointSystemType(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT");
        if (StringUtils.notText(userAgent)) return UNKNOW;
        if ((userAgent = userAgent.toLowerCase()).indexOf("ios") != -1) return IOS;
        if (userAgent.indexOf("android") != -1)return ANDROID;
        return WEB;
    }
	
	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 * @time 2018年10月13日21:00:05
	 * @author authstr
	 */
	public static String getRemoteAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getRemoteAddr();
        }
        return ip;  
    }
	
	
	/**
	 * 获取浏览器版本
	 * @param request
	 * @return
	 */
	public static String getBrower(HttpServletRequest request){
		String ua = request.getHeader("User-Agent");
		return ua;
	}
	
	
	
	
}
