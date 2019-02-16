package com.authstr.ff.utils.base;

import java.util.Collection;
import java.util.Map;

/**
 * 一些常用对象的工具类
 * @author authstr
 *@time 2018年9月17日10:20:26
 *@author authstr
 */
public class ObjectUtils {
	/**
	 * 判断一个对象是否存在
	 * @param obj 要判断的对象
	 * @return true 存在
	 * @time 2018年9月17日11:06:25
	 * @author authstr
	 */
	public static boolean isExist(Object obj){
		if (obj instanceof Collection)  return isCollectionExist((Collection)obj);//如果是集合
		if(obj instanceof Map)				return isMapExist((Map)obj);//如果是Map
		if(obj instanceof Object[])		return isArrayExist((Object[])obj);//如果是数组
		return obj!=null;
	}
	
	/**
	 * 数组是否存在,不为空,且长度不为0
	 * @param obj 要判断的数组
	 * @return true 存在
	 * @time 2018年9月17日10:28:15 
	 * @author authstr
	 */
	public static boolean isArrayExist(Object[] obj){
		return obj!=null&&obj.length!=0;
	}
	
	/**
	 * 集合是否存在
	 * @param coll 要判断的集合
	 * @return	true 存在
	 * @time 2018年9月17日10:41:32
	 * @author authstr
	 */
	public static boolean isCollectionExist(Collection coll){
		return coll!=null&&!coll.isEmpty();
	}
	
	/**
	 * Map是否存在
	 * @param kv 要判断的Map
	 * @return	true 存在
	 * @time 2018年9月17日10:57:00
	 * @author authstr
	 */
	public static boolean isMapExist(Map kv){
		return kv!=null&&kv.size()!=0;
	}
	
}
