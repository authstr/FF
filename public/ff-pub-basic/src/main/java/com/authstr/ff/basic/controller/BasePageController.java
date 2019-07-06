package com.authstr.ff.basic.controller;

import com.authstr.ff.model.platform.base.BaseConstant;
import com.authstr.ff.utils.base.SpringUtils;
import com.authstr.ff.utils.login.LoginThreadLocal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@Controller
public class BasePageController {


    /**
     * html页面的路径
     */
    private static  String systemSkin=null;

    /**
     * 获取html页面的父路径
     * @return
     */
    public String getSystemSkin(){
        if(systemSkin==null){
            systemSkin=SpringUtils.getEnvironment().getProperty(BaseConstant.PAGE_SKIN_KEY,BaseConstant.PAGE_SKIN_DEFAULT);
        }
        return systemSkin;
    }

    /**
     * 直接访问系统,跳转到首页
     * @param model
     * @return
     */
	@RequestMapping("/")
    public ModelAndView main(ModelAndView model) {
        model.addObject("username", LoginThreadLocal.get().getUsername());
		model.addObject("skin", getSystemSkin());
        model.setViewName(getSystemSkin() + "/index");
        return model;
    }
	/***
     * 共用的页面跳转,支持参数的自动添加
     * @param model
     * @param pkg
     * @param page
     * @param rqRequest
     * @return
     */
    @RequestMapping("p/view/{pkg}/{page}")
    public ModelAndView page_view(ModelAndView model,@PathVariable("pkg") String pkg, 
    		@PathVariable("page") String page,HttpServletRequest rqRequest) {
    	//将所有参数放到modelAndView
        if (rqRequest.getParameterNames().hasMoreElements()) {
            Enumeration<String> pm = rqRequest.getParameterNames();
            while (pm.hasMoreElements()) {
                String key = pm.nextElement();
                if (key.equals(pkg) || key.equals(page)) {
                    continue;
                }
                model.addObject(key, rqRequest.getParameter(key));
            }
        }
        model.addObject("skin", getSystemSkin());
        model.setViewName(getSystemSkin() + "/" + pkg + "/" + page);
        return model;
    }
}
