package com.authstr.ff.model.platform.base;

public class BaseConstant {

    /**
     * 配置文件中 系统编码 的key
     */
    final public static String SYSTEM_CODE_KEY="authstr.systemcode";

    /**
     * 配置文件中 系统编码 的默认值
     */
    final public static String SYSTEM_CODE_DEFAULT="未知系统";

    /**
     * 配置文件中 系统样式的路径 key
     */
    final public static String PAGE_SKIN_KEY="com.authstr.system.skin";

    /**
     * 配置文件中 系统样式的路径 ,默认值
     */
    final public static String PAGE_SKIN_DEFAULT="default/simple";

    /**
     * 通用的状态 非法/无效/禁用
     */
    final public static Integer COMMON_STATUS_DISABLED=-1;

    /**
     * 通用的状态 正常/有效/合法
     */
    final public static Integer COMMON_STATUS_NORMAL=0;

    /**
     * 编码的类型
     */
    final public static Integer BASE_CODE_TYPE_NULL=1;
}
