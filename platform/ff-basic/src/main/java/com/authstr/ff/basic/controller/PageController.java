package com.authstr.ff.basic.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.authstr.ff.utils.login.LoginThreadLocal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class PageController  {
	
	@Value("${com.authstr.system.skin}")
    private String skin;
	@RequestMapping("/")
    public ModelAndView main(ModelAndView model) {
        model.addObject("username", LoginThreadLocal.get().getUsername());
		model.addObject("skin", skin);
        model.setViewName(skin + "/index");
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
        model.addObject("skin", skin);
        model.setViewName(skin + "/" + pkg + "/" + page);
        return model;
    }
}
