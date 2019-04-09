package com.authstr.ff.utils.web.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.authstr.ff.utils.base.*;
import com.authstr.ff.utils.page.QueryPage;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.model.AbstractModel;
import com.authstr.ff.utils.page.AbstractReturnPage;
import com.authstr.ff.utils.page.ReturnPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AbstractDao implements InterfaceDao{
	//通过注入的EntityManager来获取数据库操作对象session
	@PersistenceContext
    private EntityManager entityManager;

	//日志对象
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args){
		System.out.println("ddd");
		HashMap a=new HashMap();
		Class<HashMap> aa;
		Class aaa=a.getClass();
		if ( Map.class.equals(aaa) ){
			System.out.println("xiangd");
		}

	}

	 /**
	  * 获取数据库操作对象,,通过session,this.entityManager.unwrap(Session.class);
	 * @return org.hibernate.Session对象
	 * @time 2018年9月17日09:11:26
	 * @author authstr
	 */
	@Override
	public Session getSession() {
	        return (Session)this.entityManager.unwrap(Session.class);
	  }

    /**
     *  刷新Session对象,在进行多个实体的操作时,需要调用该方法
     *  @time 2018年9月17日09:12:21
     *  @author authstr
     */
	@Override
    public  void flushSession() {
        this.getSession().flush();
        this.getSession().clear();
    }
	 /**
	  * 添加/保存一个entity
	 * @param entity
	 * @return 主键值
	 */
	@Override
	public Serializable save(Object entity) {
	        //HibernateValidatorUtils.validate(entity);  model验证
	        //this.modelOperatorFactory.saveMof(entity);
		// Session s=this.getSession();
		 //Transaction tx= s.beginTransaction();
		 //Serializable a= s.save(entity);
		// s.getTransaction().commit();	
		return this.getSession().save(entity);
	}
	 
	/**
	 * 添加/保存多个entity
	 * @param entityList
	 * @return 主键id的list
	 */
	@Override
	public List<Serializable> saveList(List entityList){
		List<Serializable> res=new ArrayList<Serializable>();
		for(int i=0;i<entityList.size();i++){
			res.add(this.save(entityList.get(i)));
			//每20次刷新一下session
			if(i%20==0){
				this.flushSession();
			}
        } 
		return res;
	}
	
	 /**
	  * 更新/修改一个entity
	 * @param entity
	 * @time 2018年9月25日16:15:25
	 * @author authstr
	 */
	@Override
	public void update(Object entity) {
	      this.getSession().update(entity);
	   }

    /** 
     * 更新/修改多个entity
     * @param entityList
     * @return 修改的数量
     * @time 2018年9月25日16:16:01
     * @author authstr
     */
	@Override
    public int updateList(List entityList) {
    	for(int i=0;i<entityList.size();i++){
			update(entityList.get(i));
			//每20次刷新一下session
			if(i%20==0){
				this.flushSession();
			}
        }
    	return entityList.size();
    }
	
    /**
     * 删除一个entity对象
     * @param entity
     * @time 2018年11月13日 上午11:24:35
     * @author authstr
     */
    @Override
	public void remove(Object entity) {
        this.getSession().remove(entity);
    }
    


	/**
	 * 设置query对象的一个参数
	 * @param query 要操作的query对象
	 * @param paramName 要替换的sql语句中的占位字符名称,如 :temp
	 * @param value 要替换成的值
	 * @return 被操作的query对象
	 * @time 2018年9月17日09:44:24
	 * @author authstr
	 */
	private Query setKeyValue(Query query, String paramName, Object value) {
		//如果值是集合类型的对象,如,map,set,list,向下转型并设置
		//setParameterList方法无法使用Object作为参数,必须转成正确的类型
        if (value instanceof Collection) {
            query.setParameterList(paramName, (Collection)value);
        } else if (value instanceof Object[]) {
			//如果是数组,也向下转型
            query.setParameterList(paramName, (Object[])value);
        } else {
            query.setParameter(paramName, value);
        }
        return query;
    }
	
	 /**
	  * 通过数组设置查询参数,适用于用”?”来定义参数位置
	 * @param query 要操作的query对象
	 * @param values 数组,要替换成的值
	 * @time 2018年9月25日11:27:58
	 * @author authstr
	 */
	public Query setQueryParameters(Query query, Object[] values) {
		//数组为空.直接返回
		if (!ObjectUtils.isExist(values)) return query;
		for(int i=0;i<values.length;i++){
			query.setParameter(i + 1, values[i]);
		}
		return query;
	}
	
	/**
	 * 通过两个数组设置query对象的参数,适用于用”:”+参数名称来定义参数位置
	 * @param query 要操作的query对象
	 * @param paramNames 数组,要替换的sql语句中的占位字符名称,如 :temp
	 * @param values 数组,要替换成的值
	 * @return 被操作的query对象
	 * @time 2018年9月17日10:37:32
	 * @author authstr
	 */
	public Query setQueryParameters(Query query,String[] paramNames,Object[] values){
		//字段名称为空,视为使用”?”来定义参数位置
		if(!ObjectUtils.isArrayExist(paramNames)){
			return setQueryParameters(query,values);
		}
		//参数长度验证
		Assert.isTrue(paramNames.length==values.length, 
				"参数长度不一致,parm["+paramNames.length+"],value["+values.length+"]",true);
		for(int i=0;i<paramNames.length;i++){
			setKeyValue(query, paramNames[i], values[i]);
        }
		return query;
	}
	
	/**
	 * 通过Map设置query对象的参数
	 * @param query query 要操作的query对象
	 * @param kv key表示要替换的sql语句中的占位字符名称,如 :temp,value表示要替换成的值
	 * @return 被操作的query对象
	 * @time 2018年9月17日11:12:24
	 * @author authstr
	 */
	public Query setQueryParameters(Query query,Map<String, Object> kv ){
		//map不存在
		if(!CollectionUtils.isMapExist(kv)){
			return  query;
		}
	    for (Map.Entry<String, Object> m : kv.entrySet()) {//遍历map
             this.setKeyValue(query, m.getKey(), m.getValue());
         }
		return query;
	};



	/**
	 * 执行sql语句,对数据库进行操作(map参数)
	 * @param sql sql语句
	 * @param kv sql参数
	 * @return 影响行数
	 * @time 2018年11月13日 上午11:37:44
	 * @author authstr
	 */
	public int executeSQl(String sql,Map<String,Object> kv){
		NativeQuery query=getSession().createNativeQuery(sql);
		setQueryParameters(query, kv);
		return query.executeUpdate();
	}

	/**
	 * 执行sql语句,对数据库进行操作(value参数)
	 * @param sql sql语句
	 * @param value sql参数
	 * @return 影响行数
	 * @time 2018年11月13日 上午11:39:14
	 * @author authstr
	 */
	public int executeSQl(String sql,Object[] value){
		NativeQuery query=getSession().createNativeQuery(sql);
		setQueryParameters(query, value);
		return query.executeUpdate();
	}



	/**
	 * 执行查询语句,获取返回值(方法委托)
	 * @param sql 查询sql语句
	 * @param fields sql参数 属性项数组
	 * @param values sql参数 属性值数组
	 * @param returnType 结果集封装的类型
	 * @param <T> 结果集类型
	 * @return 查询结果
	 * @time 2019年4月5日17:18:59
	 * @author authstr
	 */
	public  <T> List<T> getBySQL(String sql,String[] fields,Object[] values,Class<T> returnType){
		return getBySQL(sql,fields,values,null,null,returnType);
	}

	/**
	 * 执行查询语句,获取返回值(执行方法)
	 * @param sql 查询sql语句
	 * @param fields sql参数 属性项数组
	 * @param values sql参数 属性值数组
	 * @param firstRows 查询起始行数
	 * @param maxRows 查询数据条数
	 * @param returnType 结果集封装的类型
	 * @param <T> 结果集类型
	 * @return 查询结果
	 * @time 2019年4月5日11:23:53
	 * @author authstr
	 */
	public  <T> List<T> getBySQL(String sql,String[] fields,Object[] values,Integer firstRows,Integer maxRows,Class<T> returnType){
		//获取query对象
		Query query=createQuery(sql,firstRows,maxRows,returnType);
		//设置参数
		setQueryParameters(query,fields,values);
		List li=query.list();
		List res = converListToModel(returnType, li);
		return res;
	}


	/**
	 * 使用 属性+值 的参数方式,执行查询语句,获取返回值(方法委托)
	 * @param sql 查询sql语句
	 * @param kv sql参数,键为属性项数组,值为 属性值数组
	 * @param firstRows 查询起始行数
	 * @param maxRows 查询数据条数
	 * @param returnType 结果集封装的类型
	 * @param <T> 结果集类型
	 * @return 查询结果
	 * @time 2019年4月5日11:27:12
	 * @author authstr
	 */
	public  <T> List<T> getByMapSQL(String sql,Map<String,Object> kv,Integer firstRows,Integer maxRows,Class<T> returnType){
		String[] fields=new String[kv.size()];
		Object[] values=new Object[kv.size()];
		int i=0;
		//遍历Map
		for (Entry<String, Object> entry : kv.entrySet()) {
			fields[i]= entry.getKey();
			values[i]= entry.getValue();
		}
		return getBySQL(sql,fields,values,firstRows,maxRows,returnType);
	}


	/**
	 * 使用 属性+值 的参数方式,执行查询语句,获取返回值(方法委托)
	 * @param sql 查询sql语句
	 * @param kv sql参数,键为属性项数组,值为 属性值数组
	 * @param returnType 结果集封装的类型
	 * @param <T> 结果集类型
	 * @return 查询结果
	 * @time 2019年4月5日11:27:12
	 * @author authstr
	 */
	public  <T> List<T> getByMapSQL(String sql,Map<String,Object> kv,Class<T> returnType){
		return getByMapSQL(sql,kv,null,null,returnType);
	}


	/**
	 * 使用 属性+值 的参数方式,执行查询语句,获取一页数据(执行方法)
	 * @param sqlstring  查询sql语句
	 * @param paramAndValue sql参数,键为属性项数组,值为 属性值数组
	 * @param page	分页对象
	 * @param returnType 返回值类型
	 * @return 分页数据
	 * @time 2019年4月5日16:55:04
	 * @author authstr
	 */
	public ReturnPage queryByParamAndValue(String sqlstring,Map<String,Object> paramAndValue, ReturnPage page, Class returnType){
		page.setRecord(getByMapSQL(sqlstring,paramAndValue,page.getFirst(),page.getRows(),returnType));
		page.setTotal(getTotalCount(sqlstring,paramAndValue));
		return page;
	}

	/**
	 * 使用 属性+值 的参数方式,执行查询语句,获取一页数据(方法委托)
	 * @param sqlstring 查询sql语句
	 * @param paramAndValue sql参数,键为属性项数组,值为 属性值数组
	 * @param page 分页查询参数对象
	 * @param returnType 返回值类型
	 * @return 分页数据
	 * @time 2019年4月5日17:01:29
	 * @author author
	 */
	public ReturnPage queryByParamAndValue(String sqlstring, Map<String, Object> paramAndValue, QueryPage page, Class returnType){
		return queryByParamAndValue(sqlstring, paramAndValue,new AbstractReturnPage(page), returnType);
	}

	/**
	 * 使用 属性+值 的参数方式,执行查询语句,获取一页数据(Map)(方法委托)
	 * @param sqlstring 查询sql语句
	 * @param paramAndValue sql参数,键为属性项数组,值为 属性值数组
	 * @param page 分页查询参数对象
	 * @return 分页数据
	 * @time 2019年4月5日17:05:05
	 * @author author
	 */
	public ReturnPage queryByParamAndValue(String sqlstring, Map<String, Object> paramAndValue, QueryPage page){
		return queryByParamAndValue(sqlstring,paramAndValue,page,Map.class);
	}



	/**
	 * 使用 参数值 的参数方式,执行查询语句,获取返回值(方法委托)
	 * @param sql 查询sql语句
	 * @param values
	 * @param firstRows 查询起始行数
	 * @param maxRows 查询数据条数
	 * @param returnType 结果集封装的类型
	 * @param <T> 结果集类型
	 * @return 查询结果
	 * @return
	 * @time 2019年4月5日16:53:36
	 * @author authstr
	 */
	public  <T> List<T> getByValuesSql(String sql,Object[] values,Integer firstRows,Integer maxRows,Class<T> returnType){
		return getBySQL(sql,null,values,firstRows,maxRows,returnType);
	}

	/**
	 * 使用 参数值 的参数方式,执行查询语句,获取返回值(方法委托)
	 * @param sql 查询sql语句
	 * @param values
	 * @param returnType 结果集封装的类型
	 * @param <T> 结果集类型
	 * @return 查询结果
	 * @time 2019年4月5日16:53:36
	 * @author authstr
	 */
	public  <T> List<T> getByValuesSql(String sql,Object[] values,Class<T> returnType){
		return getByValuesSql(sql,values,null,null,returnType);
	}



	/**
	 * 使用 参数值 的参数方式,执行查询语句,获取一页数据(执行方法)
	 * @param sqlstring  查询sql语句
	 * @param values sql参数 属性值
	 * @param page 分页对象
	 * @param returnType 返回值类型
	 * @return 分页数据
	 * @time 2019年4月5日17:01:15
	 * @author authstr
	 */
	public ReturnPage queryByValue(String sqlstring,Object[] values, ReturnPage page, Class returnType){
		page.setRecord(getByValuesSql(sqlstring,values,page.getFirst(),page.getRows(),returnType));
		page.setTotal(getTotalCount(sqlstring,values));
		return page;
	}

	/**
	 * 使用 参数值 的参数方式,执行查询语句,获取一页数据(方法委托)
	 * @param sqlstring  查询sql语句
	 * @param values sql参数 属性值
	 * @param page 分页对象
	 * @param returnType 返回值类型
	 * @return 分页数据
	 * @time 2019年4月5日17:13:37
	 * @author authstr
	 */
	public ReturnPage queryByValue(String sqlstring,Object[] values, QueryPage page, Class returnType){
		return queryByValue(sqlstring,values,new AbstractReturnPage(page),returnType);
	}

	/**
	 * 使用 参数值 的参数方式,执行查询语句,获取一页数据(方法委托)
	 * @param sqlstring  查询sql语句
	 * @param values sql参数 属性值
	 * @param page 分页对象
	 * @return 分页数据
	 * @time 2019年4月5日17:13:46
	 * @author authstr
	 */
	public ReturnPage queryByValue(String sqlstring,Object[] values, QueryPage page ){
		return queryByValue(sqlstring,values,page,Map.class);
	}



	/**
	 * 取出第一个map中的num值,用于获取分页信息
	 * @param li sql查询的返回值
	 * @return num的值
	 * @time 2019年4月5日18:16:52
	 * @author authst
	 */
	public Integer getTotalNumValue(List<Map> li){
		if(li == null || li.isEmpty()){
			return 0;
		}
		//获取返回值
		Object Onum=li.get(0).get("num");
		//转换为Integer
		if(Onum instanceof BigInteger){
			BigInteger temp=(BigInteger) Onum;
			return temp.intValue();
		}else{
			return (Integer)Onum;
		}
	}


	/**
	 * 获得查询结果的总条数
	 * @param sqlstring 进行查询的sql语句
	 * @param fields 参数项数组
	 * @param values 参数值数组
	 * @return 数据条数
	 * @time 2019年4月5日18:17:11
	 * @author authst
	 */
	public Integer getTotalCount(String sqlstring, String[] fields,Object[] values) {
		String sql = " select count(1) AS num from ( " + sqlstring + " ) a";
		List<Map> li = getBySQL(sql,fields,values,Map.class);
		return getTotalNumValue(li);
	}


	/** 获得查询结果的总条数
	 * @param sqlstring 进行查询的sql语句
	 * @param kv 参数map
	 * @return 数据条数
	 * @time 2019年4月5日18:17:11
	 * @author authst
	 */
	public Integer getTotalCount(String sqlstring, Map<String, Object> kv) {
		String sql = " select count(1) AS num from ( " + sqlstring + " ) a";
		List<Map> li = getByMapSQL(sql,kv,Map.class);
		return getTotalNumValue(li);
	}

	/**
	 *  获得查询结果的总条数
	 * @param sqlstring  进行查询的sql语句
	 * @param values 参数值
	 * @return 数据条数
	 * @time 2019年4月5日18:17:11
	 * @author authst
	 */
	public Integer getTotalCount(String sqlstring, Object[] values) {
		return getTotalCount(sqlstring,null,values);
	}


	/**
	 * 创建Query对象
	 * @param sql sql语句
	 * @param firstRows 起始数据行
	 * @param maxRows 获取数据条数
	 * @param returnType 结果集封装
	 * @return Query对象
	 * @time 2019年4月8日14:57:28
	 * @author authstr
	 */
	public Query createQuery(String sql,Integer firstRows,Integer maxRows,Class returnType){
		Assert.isTrue(StringUtils.hasText(sql),"sql语句必须存在",true);
		Assert.isTrue(returnType!=null,"返回值不能为空",true);
		NativeQuery query= this.getSession().createNativeQuery(sql);
		//设置返回值类型
		if(SqlResult.class.equals(returnType)){
			//原封不动的返回数据
			query.setResultTransformer(SqlResultTransformer.SQL_INSTANCE);
		}else{
			//将数据封装为HashMap
			query.setResultTransformer(MapResultTransformer.MAP_INSTANCE);
		}
		query.setResultTransformer(new MapResultTransformer());
		//设置开始行
		if(firstRows!=null){
			query.setFirstResult(firstRows);
		}
		//设置数据条数
		if(maxRows!=null){
			query.setMaxResults(maxRows);
		}
		return query;
	}

	/**
	 * 将map的集合,转换为指定对象的集合
	 * @param clazz 要转换为的对象类型
	 * @param list 原数据
	 * @return 转换后的数据
	 * @time 2018年10月15日 上午11:35:53
	 * @author authstr
	 */
	public <T> List converListToModel(Class<T> clazz, List<Map> list){
		Assert.isTrue(clazz!=null,"返回类型不能为空",true);
		//如果返回值是SqlResult或是Map,不进行处理
		if(SqlResult.class.equals(clazz)||HashMap.class.equals(clazz) || Map.class.equals(clazz)){
			return list;
		}
		List<T> res=new ArrayList(list.size());
		//检查集合
		if(!ObjectUtils.isExist(list)){
			return res;
		}
		//遍历转换
		for(Map map:list){
			try {
				T bean = clazz.newInstance();
			    BeanUtils.populate(bean, map);
			    res.add(bean);
			} catch (Exception e) {
				log.error("无法正确的将sql结果集的某条数据转换为["+clazz.getName()+"]类型的数据;位置:AbstractDao-converListToModel;异常原因:"+e.getMessage());
			}
        }
		return res;
	}







    /**
     * 删除多个entity
     * @param clazz 要删除的实体类
     * @param ids 被删除的数据id数组
     * @return
     * @time 2018年11月13日 上午11:46:34
     * @author authstr
     */
    @Override
    public int removeIds(Class clazz, Serializable[] ids) {
        //从实体类获取数据表名称
        String tableName=ReflectionUtils.getEntityTableName(clazz);
        //如果没有get到值,抛出一个内部异常
        Assert.isTrue(StringUtils.hasText(tableName),
                "该实体类["+clazz.getSimpleName()+"]没有设置表映射!",true);
        return removeIds(tableName,ids);
    }
    /**
     * 删除多个entity
     * @param tableName 要删除的数据表名称
     * @param ids 被删除的数据id数组
     * @return
     * @time 2018年11月13日 上午11:46:34
     * @author authstr
     */
    public int removeIds(String tableName, Serializable[] ids) {
        StringBuffer sqlstring = new StringBuffer();
        sqlstring.append(" delete from " +tableName);
        sqlstring.append(" where id in (:ids) ");
        Map<String,Object> para=new HashMap<String,Object>();
        para.put("ids", ids);
        return this.executeSQl(sqlstring.toString(), para);
    }


	/**
	 * 通过一个id删除一个entiy
	 * @time 2018年11月13日14:04:43
	 * @author authstr
	 */
	@Override
	public int remove(Class clazz, Serializable id) {
	    return removeIds(clazz,new Serializable[]{id});
	}





    /**
     * 根据相关参数构建简单的sql语句进行查询(执行方法)
     * @param tableName 要查询的表名
     * @param fields	where限制的字段名称
     * @param values where限制的字段值
     * @param sqlResult 查询要返回的信息
     * @return list集合
     * @time 2018年9月17日18:00:44
     * @author authstr
     */
    public  List queryByEntity(String tableName,String[] fields,Object[] values,String sqlResult,Class returnType){
        StringBuffer sql =new StringBuffer();
        sql.append("select  "+sqlResult+"  from "+tableName+" a  where 1=1");
        for(int i=0;i<fields.length;i++){
            sql.append(" and "+fields[i]+" =  :"+fields[i]);
        }
        return getBySQL(sql.toString(),fields,values,returnType);
    }

    /**
     * 根据实体和字段参数构建简单的sql语句进行查询(执行方法)
     * @param entity 要查询的实体
     * @param fields	where限制的字段名称
     * @param values where限制的字段值
     * @return list集合
     * @time 2018年9月17日18:02:49
     * @author authstr
     */
    public <T> List<T> queryByEntity(T entity,String[] fields,Object[] values){
        String tableName=ReflectionUtils.getEntityTableName(entity);//获取实体对应的表名
        //没获取到表名,抛出一个内部异常
        Assert.isTrue(StringUtils.hasText(tableName),
                "该实体类["+entity.getClass().getSimpleName()+"]没有设置表映射!",true);
        return queryByEntity(tableName,fields,values,"*",entity.getClass());
    }




	/**
	 * 判断一个对象里一些属性的值是否具有唯一性(参数重写)
	 * @param model
	 * @param field
	 * @return
	 * @time 2018年11月1日 上午11:26:41
	 * @author authstr
	 */
	@Override
	public <T extends AbstractModel> boolean isUnique(T model,String...field){
		String tableName=ReflectionUtils.getEntityTableName(model);
        //没获取到表名,抛出一个内部异常
        Assert.isTrue(StringUtils.hasText(tableName),
                "该实体类["+model.getClass().getSimpleName()+"]没有设置表映射!",true);
		return isUnique(model,tableName,field);
	}
	
	/**
	 * 判断一个对象里一些属性的值是否具有唯一性
	 * @param model 要判断的model对象
	 * @param tableName  表名
	 * @param field 字段名称
	 * @return
	 * @time 2018年11月1日 上午11:23:56
	 * @author authstr
	 */
	public <T extends AbstractModel> boolean isUnique(T model,String tableName,String...field){
		StringBuffer sqlstring= new StringBuffer();
		List<Object> values =new ArrayList<>();//参数值集合
		//构建查询语句
		sqlstring.append("select count(*) num from " + tableName + " a where 1=1 ");
		for(int i=0;i<field.length;i++){//遍历字段,拼接查询条件
			String fieldName=field[i].trim();
			if(StringUtils.hasText(fieldName)){
                 //获取属性值
				  Object propertyValue = ReflectionUtils.getProperty(model,fieldName);
				  sqlstring.append(" and a." + fieldName + "=?");
                  //记录属性值
				  values.add(propertyValue);
			}
        }
		//判断是否存在id
		if(model.getId()!=null){
            //id不等于当前id
            sqlstring.append(" and a.id <> ?");
            //记录属性值
            values.add(model.getId());
		}
		//执行查询
		List<Map>  list=getByValuesSql(sqlstring.toString(), values.toArray(), Map.class);
		Integer num= NumberUtils.toInteger(list.get(0).get("num"));
		if(num!=null&&num==0){
		    return true;
        }
		return false;
	}
	
    /**
     * 通过id查询一个对象
     * @param clazz
     * @param id
     * @return
     * @time 2018年11月4日 下午4:19:01
     * @author authstr
     */
	@Override
    public <T> T get(Class<T> clazz, Serializable id) {
        return this.get(clazz, id, null);
    }

    /**
     * 根据id查询一个对象的指定字段
     * @param clazz
     * @param id
     * @param fields
     * @return
     * @time 2018年11月4日 下午4:18:30
     * @author authstr
     */
    @Override
    public <T> T get(Class<T> clazz, Serializable id, String[] fields) {
        //非空验证
        if (id == null || StringUtils.notText(id.toString())){
            return null;
        }
        //查询一个对象
        if (fields == null || fields.length <= 0){
            return this.getSession().get(clazz, id);
        }
        //拼接sql,查询指定字段
        StringBuffer sqlstring = new StringBuffer();
        sqlstring.append(" select  ");
        sqlstring.append(StringUtils.arrayToString(fields));
        sqlstring.append(" from " + clazz.getName());
        sqlstring.append(" where id=?");
        List<T> li= getByValuesSql(sqlstring.toString(),new Object[]{id},clazz);
        return CollectionUtils.listGetOneData(li);
    }
	
	
	
}
