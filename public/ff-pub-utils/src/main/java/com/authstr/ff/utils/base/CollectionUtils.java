package com.authstr.ff.utils.base;

import java.util.*;

/**
 * 一些集合的参与方法
 * @author authstr
 *@time 2018年9月17日10:20:26
 *@author authstr
 */
public class CollectionUtils {

	
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


	public static boolean isMapClass(Class clazz){
		return clazz!=null
				&&(
						Map.class.equals(clazz)||
						HashMap.class.equals(clazz) ||
						TreeMap.class.equals(clazz) ||
						Hashtable.class.equals(clazz) ||
						LinkedHashMap.class.equals(clazz)
				);
	}

	public static <T> T listGetOneData(List<T> list){
		return list==null||list.isEmpty()?null :list.get(0);
	}



	
}
