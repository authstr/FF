package com.authstr.ff.utils.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.encryption.ThreeDESUtils;
import com.authstr.ff.utils.http.CookieUtil;
import com.authstr.ff.utils.http.KeyConstant;
import com.authstr.ff.utils.http.RequestUtil;
import com.authstr.ff.utils.http.SessionUtil;

/**
 * 登录相关工具类
 * @time 2018年10月13日20:56:10
 * @author authstr
 */
public class LoginUtils {
	private static Logger log = LogManager.getLogger(LoginUtils.class);

	
	/**
	 * 将json转换为LoginInfo对象
	 * @param json
	 * @return
	 * @time 2018年10月13日22:06:47
	 * @author authstr
	 */
	public static LoginInfo fromJSON(String json){
		return JSON.parseObject(json, LoginInfo.class);
	}
	
	
	/**
	 * cookie中是否存在Key为 KeyConstant.COOKIE_ID_AUTHSTR_A的对象
	 * @param request
	 * @return
	 * @time 2018年10月13日21:30:59
	 * @author authstr
	 */
	public static boolean hasCookie(HttpServletRequest request){
		return CookieUtil.getCookie(request, KeyConstant.COOKIE_ID_AUTHSTR_A) != null;
	}
	
	/**
	 * 使用登录信息对象创建Cookie
	 * @param response
	 * @param loginInfo
	 * @time 2018年10月13日21:56:48
	 * @author authstr
	 */
	public static void createCookie(HttpServletResponse response,LoginInfo loginInfo){
		CookieUtil.createCookie(
				response,
				KeyConstant.COOKIE_ID_AUTHSTR_A,
				ThreeDESUtils.encode(KeyConstant.SecKey_KEY.getBytes(), JSON.toJSONString(loginInfo)) ,
				-1
				);
	}
	
	/**
	 * 将登录信息对象设置到Session中
	 * @param session
	 * @param loginInfo
	 * @time 2018年10月13日21:57:04
	 * @author authstr
	 */
	public static void createSession(HttpSession session,LoginInfo loginInfo){
		session.setAttribute(KeyConstant.SESSION_ID_AUTHSTR_B,loginInfo);
	}
	
    /**
     * 从cookie获取登录信息对象
     * @param request
     * @return
     * @time 2018年10月13日22:03:38
     * @author authstr
     */
    public static LoginInfo getLoginInfoByCookie(HttpServletRequest request){
    	String lpt = CookieUtil.getCookieValue(request,KeyConstant.COOKIE_ID_AUTHSTR_A);
		if(!StringUtils.hasText(lpt))return null;
		return LoginUtils.fromJSON(ThreeDESUtils.decode(KeyConstant.SecKey_KEY.getBytes() ,lpt));
    }
    
	/**
	 * 获取session的LoginInfo对象
	 * @param session
	 * @return
	 * @time 2018年10月13日21:37:15
	 * @author authstr
	 */
	public static LoginInfo getLoginInfoBySession(HttpSession session){
		return (LoginInfo)SessionUtil.getSession(session, KeyConstant.SESSION_ID_AUTHSTR_B);
	}
	
	/**
	 * session中是否存在Key为KeyConstant.SESSION_ID_AUTHSTR_B的对象
	 * @param session
	 * @return
	 * @see KeyConstant.SESSION_ID_AUTHSTR_B
	 * @time 2018年10月13日21:30:48
	 * @author authstr
	 */
	public static boolean hasSession(HttpSession session){
		return SessionUtil.hasSession(session,KeyConstant.SESSION_ID_AUTHSTR_B);
	}
	
	/**
	 *  移除session中key为KeyConstant.SESSION_ID_AUTHSTR_B的值
	 * @param session
	 * @time 2018年10月13日21:42:33
	 * @see KeyConstant.SESSION_ID_AUTHSTR_B
	 * @author authstr
	 */
	public static void removeSession(HttpSession session){
		SessionUtil.removeSession(session,KeyConstant.SESSION_ID_AUTHSTR_B);
	}
	
	/**
	 * 系统登出
	 * @param request
	 * @param response
	 * @time 2018年10月14日 下午5:59:03
	 * @author authstr
	 */
	public static void logout( HttpServletRequest request, HttpServletResponse response){
		LoginInfo li = LoginUtils.getLoginInfoByCookie(request);
		if(li !=null){
			//这里应该记录日志
			log.info("退出登录,用户id["+li.getUserID()+"],用户名称["+li.getUsername()+"],终端类型["+RequestUtil.getEndPointType(request)+"]");
		}else{
			log.error("退出成功，但是cookie里的值为空");
		}
        HttpSession session = request.getSession();
        session.invalidate();
        CookieUtil.removeCookie(response, KeyConstant.COOKIE_ID_AUTHSTR_A);
	}
	
}
