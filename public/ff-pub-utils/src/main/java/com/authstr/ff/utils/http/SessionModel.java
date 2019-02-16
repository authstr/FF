package com.authstr.ff.utils.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.encryption.SecKey;
import com.authstr.ff.utils.encryption.ThreeDESUtils;


/***
 * 本类是对session的重新封装
 * @author YangHang
 *
 */
public class SessionModel   {
	private static Logger log = LogManager.getLogger(SessionModel.class);
	private static final long serialVersionUID = 5848759623049932651L;
	
	private String ip;
	
	private String brower;
	
	private String uid;
	private String username;
	
	private String dates;
	private String endpoint;
	
	
	public static SessionModel fromJSON(String json){
		return JSON.parseObject(json, SessionModel.class);
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrower() {
		return brower;
	}
	public void setBrower(String brower) {
		this.brower = brower;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}





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
	public static String getBrower(HttpServletRequest request){
		String ua = request.getHeader("User-Agent");
		return ua;
	}
	


	public static boolean hasSession(HttpSession session){
		return session.getAttribute(KeyConstant.SESSION_ID_AUTHSTR_B) != null;
	}
	public static SessionModel getSessionModel(HttpSession session){
		return (SessionModel)session.getAttribute(KeyConstant.SESSION_ID_AUTHSTR_B);
	}
	public static void removeSession(HttpSession session){
		session.removeAttribute(KeyConstant.SESSION_ID_AUTHSTR_B);
	}

	public static SessionModel getByCookie(HttpServletRequest request){
		String lpt = CookieUtil.getCookieValue(request,KeyConstant.COOKIE_ID_AUTHSTR_A);
		if(!StringUtils.hasText(lpt))return null;
		log.info("lpt="+lpt);
		return SessionModel.fromJSON(ThreeDESUtils.decode(SecKey.KEY.getBytes() ,lpt));
	}
	public static boolean validateUserIsRight(SessionModel sm,
			HttpServletRequest req) {
//		String currentBrower = getBrower(req);
//		if(StringUtils.notText(currentBrower) || 
//				currentBrower.indexOf("Commons-HttpClient")!=-1 || 
//				currentBrower.indexOf("HttpClient")!=-1){
//			return true;//代表是通过httpclient的get方法请求的
//		}
//		String currentIp = getRemoteAddress(req);
//		if((currentIp.equals("127.0.0.1") || currentIp.trim().toLowerCase().equals("localhost"))
//			&& currentBrower.equals(sm.getBrower())){
//			return true;//代表是通过post请求的
//		}
//		if(!currentBrower.equals(sm.getBrower())){
//			return false;
//		}
		return true;
	}
	
}
