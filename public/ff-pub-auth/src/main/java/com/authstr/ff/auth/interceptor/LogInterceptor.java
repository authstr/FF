package com.authstr.ff.auth.interceptor;

import com.authstr.ff.auth.log.LogThreadLocal;
import com.authstr.ff.model.platform.base.BaseBusinessLog;
import com.authstr.ff.model.platform.base.BaseConstant;
import com.authstr.ff.utils.base.SpringUtils;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.http.RequestUtil;
import com.authstr.ff.utils.login.LoginInfo;
import com.authstr.ff.utils.login.LoginThreadLocal;
import com.authstr.ff.utils.web.sevice.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.NumberUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 日志对象拦截器
 * @time 2018年10月12日16:26:32
 * @author authstr
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
	protected Logger log = LoggerFactory.getLogger(this.getClass());


	/**
	 * 在处理日志对象
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		this.logInfoPersistentAndClean();
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
		//创建业务日志实体类
		BaseBusinessLog businessLog=new BaseBusinessLog();
		//所属系统 从配置文件获取
		businessLog.setSystem_name(SpringUtils.getEnvironment().getProperty(BaseConstant.SYSTEM_CODE_KEY,BaseConstant.SYSTEM_CODE_DEFAULT));
		//请求进入时间
		businessLog.setEnter_time(new Date());
		//来源的ip
		businessLog.setSource_ip(RequestUtil.getRemoteAddress(req));
		//终端的类型
		businessLog.setSource_type(RequestUtil.getEndPointType(req));
		//如果终端是手机
		if( RequestUtil.MOBILE.equals(businessLog.getSource_type()) ){
			//获取手机的信息
			businessLog.setBrowser_info(RequestUtil.getEndPointType(req));

		}else if(RequestUtil.WEB.equals(businessLog.getSource_type())){
			//是网页
			businessLog.setBrowser_info(RequestUtil.getBrower(req));
		}else{
			businessLog.setBrowser_info("未知");
		}
		//请求的url
		businessLog.setServer_uri(uri);
		//请求的参数
//		businessLog.setParams(req.getParameterMap().toString());
		//操作类型
		businessLog.setTypy("未知");
		//操作用户
		LoginInfo loginInfo= LoginThreadLocal.get();
		if(loginInfo!=null){
			businessLog.setOperate_user(NumberUtils.parseNumber(loginInfo.getUserID(),Integer.class) );
			businessLog.setCreator_id(loginInfo.getUserID());
		}else{
			businessLog.setOperate_user(null);
		}
		//业务过程
		//业务结果

		//保存到本地线程
		LogThreadLocal.set(businessLog);
		return true;
	}

	/**
	 * 结束请求,日志信息持久化并清除LogThreadLocal
	 * @time 2018年10月14日 下午3:15:53
	 * @author authstr
	 */
	private void logInfoPersistentAndClean(){
		if(LogThreadLocal.get()!=null){
			try {
				BaseBusinessLog businessLog=LogThreadLocal.get();
				businessLog.setEnd_time(new Date());
				//获取服务层对象
				AbstractService service=SpringUtils.getBean(AbstractService.class,"basicServiceImpl");
				Assert.isTrue(service!=null ,"无法获service对象");
				service.save(businessLog);
				LogThreadLocal.remove();
			} catch (Exception e) {
				log.error("业务日志信息持久化失败,无法保存日志对象或清除本地线程!");
				e.printStackTrace();
			}
		}else {
			log.error("业务日志信息持久化失败,无法从本地线程获取日志对象!");
		}
	}


}
