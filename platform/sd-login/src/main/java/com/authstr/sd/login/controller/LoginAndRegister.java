package com.authstr.sd.login.controller;


import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.authstr.sd.login.utils.ConstantsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.authstr.ff.model.platform.base.BaseUser;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.exception.MsgException;
import com.authstr.ff.utils.http.CookieUtil;
import com.authstr.ff.utils.http.RequestUtil;
import com.authstr.ff.utils.login.LoginInfo;
import com.authstr.ff.utils.login.LoginUtil;
import com.authstr.ff.utils.web.controller.AbstractController;
import com.authstr.sd.login.service.inter.LoginAndRegisterService;
import com.authstr.sd.login.utils.LoginInfoEnum;
import com.authstr.sd.login.utils.ValidateCode;


@Controller
public class LoginAndRegister extends AbstractController{
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public LoginAndRegisterService loginService;
	
	@Value("${authstr.login.successpage}")
    private String successpage;
	
	String login_page_vm = "login";
	
	String register_page_vm = "register";
	
	@RequestMapping("/")
    public ModelAndView main(ModelAndView model,HttpServletRequest request, HttpServletResponse response,
    		  String username, String pwd, String verify) {
		return loginservice(model,request,response,username,pwd,verify);
    }
	@RequestMapping("/login")
    public ModelAndView login(ModelAndView model,HttpServletRequest request, HttpServletResponse response,
    		  String username, String pwd, String verify) {
		return loginservice(model,request,response,username,pwd,verify);
    }
	
	//跳转到注册页面
	@RequestMapping("/register_page")
	public ModelAndView register_page(ModelAndView model,HttpServletRequest request, HttpServletResponse response) {
		model.setViewName(register_page_vm);
		return model;
	}
	
	//处理注册操作
	@RequestMapping("/register")
	public Map register(HttpServletRequest request, HttpServletResponse response,
			String username,String pwd,String email,String verify) {
		Map m=super.success();
		//model.setViewName(register_page_vm);
		m.put("msg", loginService.register(username, pwd, email));
		//移除验证码cookie
		CookieUtil.removeCookie(response, ConstantsUtils.COOKIE_SCAPTCHA);
		//model.setViewName(login_page_vm);
		return m;
	}

	/**
	 * 处理登录请求
	 * @param model
	 * @param request
	 * @param response
	 * @param username
	 * @param pwd
	 * @return
	 * @time 2018年10月14日 下午5:31:38
	 * @author authstr
	 */
	private ModelAndView loginservice(ModelAndView model, HttpServletRequest request, HttpServletResponse response,
			 String username, String pwd,String verify) {
		HttpSession session=request.getSession();
		if (StringUtils.notText(username) && StringUtils.notText(pwd)) {//如果没有用户名和密码信息,判断是否已登录
			if(LoginUtil.hasSession(session)||LoginUtil.hasCookie(request)){//如果cookie或session有登录信息,则已登录
				LoginInfo li=LoginUtil.getLoginInfoByCookie(request);//获取登录信息
				//这里通过登录信息,获取要跳转到主页
				//这里还应该判断一下登录信息是否正确
			    model.addObject("path", successpage);
                model.setViewName("success");
                log.info("已登录,跳转到["+successpage+"]");
                return model;
			}else{
				 model.setViewName(login_page_vm);//到login页面
				 return model;
			}
        }
		log.info("进入用户登录,ip["+RequestUtil.getRemoteAddress(request)+"]");
		log.info("用户名["+username+"]");
		log.info("密码["+pwd+"]");
		log.info("验证码["+verify+"]");
    	try {
    		//验证验证码是否正确
    		validateCode(verify,request);
    		//登录验证
    		BaseUser u = loginService.validate(username, pwd);
    		//保存登录状态
    		LoginInfo loginInfo=new LoginInfo();
    		loginInfo.setUserID(String.valueOf(u.getId()));
    		loginInfo.setUsername(u.getUsername());
    		LoginUtil.createCookie(response, loginInfo);
    		//保存信息
            model.addObject("path", successpage);
            model.setViewName("success");
            log.info("登录成功!跳转到["+successpage+"]");
        } catch (MsgException e) {
            model.addObject("msg", e.getMessage());
            log.error("登录失败,原因["+e.getMessage()+"]");
            model.setViewName("login");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登录异常,原因["+e.getMessage()+"]");
            model.addObject("msg", LoginInfoEnum.Login_Unknow_Error);
            model.setViewName("login");
        }
		//移除验证码cookie
		CookieUtil.removeCookie(response, ConstantsUtils.COOKIE_SCAPTCHA);

		return model;
	}
	@RequestMapping("/logout")
	public String logout(ModelAndView model, HttpServletRequest request, HttpServletResponse response){
		LoginUtil.logout(request, response);
        return "redirect:/login";
	}
	
	private void validateCode(String code, HttpServletRequest request) {
        Cookie cookie = CookieUtil.getCookie(request, ConstantsUtils.COOKIE_SCAPTCHA);
    	Assert.isTrue(cookie !=null,
				LoginInfoEnum.The_VerfiCode_Is_Wrong.getCode(),LoginInfoEnum.The_VerfiCode_Is_Wrong.getExplain());//验证码错误
        Assert.isTrue(StringUtils.hasText(code),
				LoginInfoEnum.The_VerfiCode_Is_Wrong.getCode(),LoginInfoEnum.The_VerfiCode_Is_Wrong.getExplain());//验证码错误
        Boolean temp = code.equalsIgnoreCase(cookie.getValue());
        Assert.isTrue(temp,
				LoginInfoEnum.The_VerfiCode_Is_Wrong.getCode(),LoginInfoEnum.The_VerfiCode_Is_Wrong.getExplain());//验证码错误
    }
	
    @RequestMapping("scaptcha")
    public void generateScaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode instance = new ValidateCode();
        CookieUtil.setCookie(response, "scaptcha", instance.getCode().toUpperCase(), null, 1 * 60);
        try {
            instance.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
