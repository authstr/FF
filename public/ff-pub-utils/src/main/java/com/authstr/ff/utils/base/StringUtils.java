package com.authstr.ff.utils.base;

/**
 * String相关操作工具类
 * @time 2018年11月4日 下午4:11:36
 * @author authstr
 * @version V1.0
 */
 
public class StringUtils {

	public static void main(String[] args) {
	}
	/** 
	 * <p>字符串非null<br> 
	 * @param str 要判断的字符串
	 * @return	true为非空
	 * @Time 2018年8月31日17:04:01
	 * @author authstr
	 */
	public static boolean isNotNull(String str){
		return str!=null;
	}
	
	/**
	 * 所有字符串非null
	 * @param str 要判断的多个字符串
	 * @return 	true为所有的字符串都不为null
	 * @Time 2018年8月31日17:04:22
	 * @author authstr
	 */
	public static boolean isAllNotNull(String... str){
		boolean res=true;
		for(int i=0;i<str.length;i++){
			if(str[i]==null){
				res=false;
				break;
			}
        }
		return res;
	}
	
	/**
	 * 字符串不为null且去除空格后不为"" 
	 * @param str 要判断的字符串
	 * @return	true不为null且去除空格后不为""
	 * @Time 2018年8月31日17:14:51
	 * @author authstr
	 */
	public static boolean hasText(String str){
		return str!=null&&str.trim().length()!=0;
	}
	
	/**
	 * 所有字符串不为null且去除空格后不为"" 
	 * @param str 要判断的多个字符串
	 * @return	
	 * @Time 2018年8月31日17:19:09
	 * @author authstr
	 */
	public static boolean hasText(String... str){
		boolean res=true;
		for(int i=0;i<str.length;i++){
			if(!StringUtils.hasText(str[i])){
				res=false;
				break;
			}
        }
		return res;
	}
	
	/**
	 * 字符串为null,或者去除空格后为""
	 * @param str
	 * @return
	 * @time 2018年10月14日 下午5:41:03
	 * @author authstr
	 */
	public static boolean notText(String str){
		return !hasText(str);
	}
	
	/**
	 * 所有字符串为null,或者去除空格后为""
	 * @param str
	 * @return
	 * @time 2018年10月14日 下午5:43:06
	 * @author authstr
	 */
	public static boolean notText(String... str){
		boolean res=true;
		for(int i=0;i<str.length;i++){
			if(!StringUtils.notText(str[i])){
				res=false;
				break;
			}
        }
		return res;
	}
	
	/**
	 * 首字母大写
	 * @param str 要操作的字符串
	 * @return 大写后的字符串
	 * @time 2018年9月18日22:11:09
	 * @author authstr
	 */
	public static String toUpperCaseFirstOne(String str){
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	
	/**
	 * 首字母小写
	 * @param str 要操作的字符串
	 * @return 小写后的字符串
	 * @time 2018年9月18日22:12:07
	 * @author authstr
	 */
	public static String toLowerCaseFirstOne(String str){
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}
	
	
	/**
	 * String数组合并成String(参数重写)
	 * @param array 数组
	 * @return
	 * @time 2018年11月4日16:14:48
	 * @author authstr
	 */
	public static String arrayToString(String[] array){
		return arrayToString(array,",");
	}
	
	/**
	 * String数组合并成String
	 * @param array 数组
	 * @param connectSymbol 连接符
	 * @return
	 * @time 2018年11月4日 下午4:14:18
	 * @author authstr
	 */
	public static String arrayToString(String[] array,String connectSymbol){
		 connectSymbol = connectSymbol == null ? "" : connectSymbol;
		StringBuffer res=new StringBuffer();
		for(int i=0;i<array.length;i++){
			res.append(array[i]);
			if(i!=(array.length-1)){
				res.append(connectSymbol);
			}
        }
		return res.toString();
	}
	
    /**
     * 对字符串中的指定字符进行替换
     * @param inString 要操作的字符串
     * @param oldPattern 要替换的字符
     * @param newPattern 要替换成的字符
     * @return 替换后的字符串
     * @time 2018年11月1日 上午11:06:21
     * @author authstr
     */
    public static String replace(String inString, String oldPattern, String newPattern) {
        if (inString == null)  return null;
        if (oldPattern == null || newPattern == null)  return inString;
        StringBuffer sbuf = new StringBuffer();
        int pos = 0;
        int index = inString.indexOf(oldPattern);
        int patLen = oldPattern.length();
        while (index >= 0) {
            sbuf.append(inString.substring(pos, index));
            sbuf.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }
        sbuf.append(inString.substring(pos));
        return sbuf.toString();
    }

}
