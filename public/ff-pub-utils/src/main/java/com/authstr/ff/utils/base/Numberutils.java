package com.authstr.ff.utils.base;



/**
 * 数字相关类的工具
 * @time 2018年11月1日 上午11:03:33
 * @author authstr
 * @version V1.0
 */

public class Numberutils {
	   /**
	 * 将一个对象转换为Integer(参数重写)
	 * @param obj
	 * @return
	 * @time 2018年11月1日 上午11:14:35
	 * @author authstr
	 */
	public static Integer toInteger(Object obj) {
	        return toInteger(obj, false);
	    }

	    /**
	     * 将一个对象转换为Integer
	     * @param obj 要转换的对象
	     * @param isThrowException 在转换失败时是否抛出异常
	     * @return
	     * @time 2018年11月1日 上午11:13:34
	     * @author authstr
	     */
	    public static Integer toInteger(Object obj, boolean isThrowException) {
	        Integer i;
	        try {
	            if (obj == null)  return 0;
	            String str = obj.toString();
	            str = StringUtils.replace(str, ",", "");
	            i = new Integer(str);
	        }
	        catch (Exception e) {
	            if (isThrowException) throw new IllegalArgumentException("类["+obj.getClass().getName()+"]转换为Integer失败!", e);
	            i = null;
	        }
	        return i;
	    }

	    /**
	     *  将一个对象转换为Integer
	     * @param obj 要转换的对象
	     * @param defaultValue	 在转换失败时,使用的默认值
	     * @return
	     * @time 2018年11月1日 上午11:14:59
	     * @author authstr
	     */
	    public static Integer toInteger(Object obj, Integer defaultValue) {
	        Integer re;
	        try {
	            String str = obj.toString();
	            str = StringUtils.replace(str, ",", "");
	            re = new Integer(str);
	        }
	        catch (Exception e) {
	            re = defaultValue;
	        }
	        return re;
	    }

}
