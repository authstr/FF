package com.authstr.ff.model.platform.base;

public class LoginConstant {
    //是否验证登录的配置文件键值和默认值
    final public static String IS_LOGIN_INTERCEPTOR_KEY="authstr.login.interceptor";
    final public static Boolean IS_LOGIN_INTERCEPTOR_DEFAULT=true;

    //登录验证排除的路径 的键
    final public static String LOGIN_INTERCEPTOR_EXCLUDE_KEY="authstr.login.interceptor.exclude";
    /**
     * 登录验证排除的路径 的默认值
     */
    final public static String LOGIN_INTERCEPTOR_EXCLUDE_DEFAULT="/login,/logout,/**/login,/,/lgur,/scaptcha";


    //配置文件中的登录url 的键和默认值
    final public static String LOGIN_URL_KEY="authstr.login.interceptor.url";
    final public static String LOGIN_URL_DEFAULT="/ff-login/login";


    /**
     * 验证码的 cookie 键值
     */
    final public static String  COOKIE_SCAPTCHA="scaptcha";

    /**
     * 登录成功跳转页  配置文件 键
     */
    final public static String LOGIN_SUCCESS_SKIP_PAGE_KEY="authstr.login.successpage";

    /**
     * 默认的登录成功跳转页
     */
    final public static String LOGIN_SUCCESS_SKIP_PAGE_DEFAULT="/ff-basic/p/view/test/index";



}
