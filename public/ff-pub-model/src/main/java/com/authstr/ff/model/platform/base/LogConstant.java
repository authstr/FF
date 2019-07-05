package com.authstr.ff.model.platform.base;

public class LogConstant {


    /**
     * 是否进行业务日志的配置文件 键值
     */
    final public static  String IS_LOG_INTERCEPTOR_KEY="authstr.log.interceptor";
    /**
     * 是否进行业务日志的配置文件 默认值
     */
    final public static  Boolean IS_LOG_INTERCEPTOR_DEFAULT=true;

    //日志验证排除的路径 的键
    final public static  String LOG_INTERCEPTOR_EXCLUDE_KEY="authstr.log.interceptor.exclude";

    /**
     * 日志验证排除的路径 的默认值
     */
    final public static  String LOG_INTERCEPTOR_EXCLUDE_DEFAULT="/**/skins/**,/**/error/**";


}
