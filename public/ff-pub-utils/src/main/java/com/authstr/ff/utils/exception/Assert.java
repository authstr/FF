package com.authstr.ff.utils.exception;


/**
 * 用于快捷的抛出异常
 * @author authstr
 */
public class Assert {
	 /**
	  * 为false时抛出一个"未知错误"的异常
	 * @param exp 为false抛出异常
	 * @time 2018年9月17日10:10:33
	 * @author authstr
	 */
	public static void isTrue(boolean exp) {
	        Assert.isTrue(exp, "执行过程中出现未知错误!");
	    }

	    /**
	     *  为false时抛出一个指定message的异常
	     * @param exp	为false抛出异常
	     * @param message 该异常要显示的信息
	     * @time 2018年9月17日10:13:05
	     * @author authstr
	     */
	    public static void isTrue(boolean exp, String message) {
	        Assert.isTrue(exp, message, false);
	    }
	    
	    public static void isTrue(boolean exp, String code,String message) {
	        Assert.isTrue(exp, code, message,null,false);
	    }

	    /**
	     * 为false时抛出一个指定message的消息异常或者错误异常
	     * @param exp 为false抛出异常
	     * @param message 该异常要显示的信息
	     * @param isInside 该异常是否为错误异常
	     * @time 2018年9月17日10:13:17
	     * @author authstr
	     */
	    public static void isTrue(boolean exp, String message, boolean isInside) {
	    	 Assert.isTrue(exp, null, message,null,isInside);
	    }
	    
	    
	    /**
	     * 为false时抛出一个指定信息的消息异常或者错误异常
	     * @param exp 为false抛出异常
	     * @param message 异常的详细说明
	     * @param data	该异常要附带的数据信息
	     * @param isInside 该异常是否为错误异常
	     * @time 2018年9月26日10:11:19
	     * @author authstr
	     */
	    public static void isTrue(boolean exp, String code, String message,Object data,boolean isInside){
	    	 if (!exp) {
				if (!isInside) {
					throw new MsgException(code,message,data);
				}else{
					throw new ErrorException(code,message,data);
				}
	    	 }
	    }

		/**
		 * 对象为空时,抛出一个异常
		 * @param o
		 * @time 2019年3月21日20:07:56
		 */
		public static void notNull(Object o){
			if(o==null){
				throw new ErrorException("系统未知异常:null");
			}
		}
}
