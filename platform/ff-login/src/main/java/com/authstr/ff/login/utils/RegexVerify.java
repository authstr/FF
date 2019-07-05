package com.authstr.ff.login.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用验证的工具栏
 * @time 2018年10月31日 下午7:45:58
 * @author authstr
 * @version V1.0
 */

public class RegexVerify {
	
	
	public static void main(String[] args) {
		System.out.println();
		String mobeil="a15236307900";
		System.out.println(regexPassword(mobeil));

	}
	
	public static boolean  regex(String regex,String value ){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return  m.matches();
	}
	
	/**
	 * 验证用户密码(长度在6-18之间，只能包含字符、数字和下划线)
	 * @param password
	 * @return
	 * @time 2018年10月31日 下午7:53:55
	 * @author authstr
	 */
	public static boolean  regexPassword(String password){
		String regex="^\\w{6,18}$";
		return regex(regex,password);
	}
	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 * @time 2018年10月31日 下午7:58:03
	 * @author authstr
	 */
	public static boolean  regexEmail(String email){
		String regex="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		return regex(regex,email);
	}
	
	

	
	
	
}
