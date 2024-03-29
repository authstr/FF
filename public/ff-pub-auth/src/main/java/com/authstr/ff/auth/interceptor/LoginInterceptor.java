package com.authstr.ff.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.authstr.ff.model.platform.base.LoginConstant;
import com.authstr.ff.utils.login.LoginInfo;
import com.authstr.ff.utils.login.LoginThreadLocal;
import com.authstr.ff.utils.login.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.authstr.ff.utils.base.SpringUtils;

/**
 * 用户登录状态拦截器
 * @time 2018年10月12日16:26:32
 * @author authstr
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	Logger log = LoggerFactory.getLogger(this.getClass());


	/**
	 * 在整个请求完成后,清理LoginThreadLocal
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		this.cleanLoginThreadLocal();
	}

	

	/***
	 * 先检查session中是不是有，有的话，直接过。如果没有再检查cookie中是不是有，有的话直接过。否则跳到登录页
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
//		log.info("对请求["+uri+"]验证是否登录");
		LoginInfo cli= LoginUtils.getLoginInfoByCookie(request);
		LoginInfo sli= LoginUtils.getLoginInfoBySession(session);
		if(cli==null||cli.getUserID()==null){//如果cookie没登录信息,或者拿不到uid,视为没登录
			//重置session
			session.invalidate();
			session = req.getSession(true);
			sli=null;
		}else{
			if(sli==null||sli.getUserID()==null||!cli.getUserID().equals(sli.getUserID())){
				//如果session中没有登录信息,或者拿不到uid,或者sesion的登录信息与cookie的不一致
				//重置session,在session中创建
				session.invalidate();
				session = req.getSession(true);
				LoginUtils.createSession(session, cli);
				sli = cli;
			}
		}
		if(sli != null){//存在登录信息,不拦截
			LoginThreadLocal.set(sli);//保存到本地线程
//			log.info("已登录,允许请求");
			return true;
		}else{//跳转到登录
			StringBuffer url = getLoginAndRequestUrl(req, uri);
			res.sendRedirect(url.toString());
//			log.info("未登录,跳转到["+url+"]页面");
			return false;
		}
	}

	/**
	 * 清除LoginThreadLocal对象的登录信息
	 * @time 2018年10月14日 下午3:15:53
	 * @author authstr
	 */
	private void cleanLoginThreadLocal(){
		if(LoginThreadLocal.get()!=null){
			String uid = (String)LoginThreadLocal.get().getUserID();
//			log.info(uid+":准备注销LoginThreadLocal");
			LoginThreadLocal.remove();
			try {
//				log.info("注销结束："+(LoginThreadLocal.get()==null?uid+"注销成功.":LoginThreadLocal.get().getUserID()+"注销失败"));
			} catch (Exception e) {
				log.warn("LoginThreadLocal注销出错!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取登录页面的url
	 * @param req
	 * @param uri
	 * @return
	 * @time 2018年10月14日 下午3:16:47
	 * @author authstr
	 */
	private StringBuffer getLoginAndRequestUrl(HttpServletRequest req,
			String uri){
		StringBuffer url = new StringBuffer();
		url.append(SpringUtils.getContext().getEnvironment().getProperty(LoginConstant.LOGIN_URL_KEY,LoginConstant.LOGIN_URL_DEFAULT));
		return url;
	}
	
}
