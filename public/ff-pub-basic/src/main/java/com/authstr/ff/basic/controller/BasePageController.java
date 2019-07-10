package com.authstr.ff.basic.controller;

import com.authstr.ff.model.platform.base.BaseConstant;
import com.authstr.ff.utils.base.SpringUtils;
import com.authstr.ff.utils.login.LoginThreadLocal;
import com.authstr.ff.utils.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * 页面相关控制层的 父类
 * 用于进行页面的默认跳转
 */
@Component
public class BasePageController extends AbstractController {

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
     * 将一些常用的参数设置到ModelAndView
     * @param model
     */
    public void setCommonPara(ModelAndView model){
        if( LoginThreadLocal.get()!=null){
            model.addObject("userid", LoginThreadLocal.get().getUserID());
            model.addObject("username", LoginThreadLocal.get().getUsername());
        }
        model.addObject("skin", getSystemSkin());
    }

    /**
     * 将request所有参数放到ModelAndView
     * @param model
     * @param request
     */
    public void setModelAndViewPara(ModelAndView model,HttpServletRequest request){
        //将所有参数放到modelAndView
        if (request.getParameterNames().hasMoreElements()) {
            Enumeration<String> pm = request.getParameterNames();
            while (pm.hasMoreElements()) {
                String key = pm.nextElement();
                model.addObject(key, request.getParameter(key));
            }
        }
    }

    /**
     * 直接访问系统,跳转到首页
     * @param model
     * @return
     */
	@RequestMapping("/")
    public ModelAndView main(ModelAndView model) {
        setCommonPara(model);
        model.setViewName(getSystemSkin() + "/index");
        return model;
    }
	/***
     * 共用的页面跳转,支持参数的自动添加
     * @param model
     * @param pkg
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("p/view/{pkg}/{page}")
    public ModelAndView page_view(ModelAndView model,@PathVariable("pkg") String pkg, @PathVariable("page") String page,HttpServletRequest request) {
        setModelAndViewPara(model,request);
        setCommonPara(model);
        model.setViewName(getSystemSkin() + "/" + pkg + "/" + page);
        return model;
    }

}
