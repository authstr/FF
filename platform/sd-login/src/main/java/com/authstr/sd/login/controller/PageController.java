package com.authstr.sd.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController  {
	/***
     * 登录成功后跳转的页面
     */
//    @Value("${newcapec.common.login.successpage}")
//    private String successpage;
//    
//	@Autowired
//    private LoginService loginService;
//	
//	@RequestMapping("/")
//    public ModelAndView main(ModelAndView model,HttpServletRequest request, HttpServletResponse response,
//    		String schoolcode, String username, String pwd, String rtservice) {
//		return loginservice(model,request,response,schoolcode,username,pwd,rtservice);
//    }
//	
//	@RequestMapping("/login")
//    public ModelAndView login(ModelAndView model,HttpServletRequest request, HttpServletResponse response,
//    		String schoolcode, String username, String pwd, String rtservice) {
//		return loginservice(model,request,response,schoolcode,username,pwd,rtservice);
//    }
//	
//	private  ModelAndView loginservice(ModelAndView model,HttpServletRequest request, HttpServletResponse response,
//    		String schoolcode, String username, String pwd, String rtservice) {
//		HttpSession session = request.getSession();
//		if (StringUtils.notText(schoolcode) && StringUtils.notText(username) && StringUtils.notText(pwd)) {
//            if (SessionModel.hasSession(session) || CookieUtil.getCookie(request, UserStateTools.COOKIE_ID_ZZSZHXY) != null) {
//            	 model.addObject("path", successpage);
//                model.setViewName("success");
//            } else {
//                model.setViewName("index");
//            }
//        }else{
//        	try {
//                //验证校区代码 用户名 密码是否正确
//        		UserVM vm = loginService.validate(schoolcode,username, pwd);
//                SessionModel.createCookie(response, request, vm);
//                model.addObject("path", successpage);
//                model.setViewName("success");
//            } catch (CapecException e) {
//                model.addObject("msg", e.getMessage());
//                model.setViewName("index");
//            } catch (Exception e) {
//                e.printStackTrace();
//                model.addObject("msg", LoginConstants.Login_Unknow_Error);
//                model.setViewName("index");
//            }
//        } 
//        return model;
//	}
//	@RequestMapping("/logout")
//    public String logout(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        session.invalidate();
//        CookieUtil.removeCookie(response, UserStateTools.COOKIE_ID_ZZSZHXY);
//        return "redirect:/login";
//    }
}
