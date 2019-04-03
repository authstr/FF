package com.authstr.ff.utils.web.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.authstr.ff.utils.base.Numberutils;
import com.authstr.ff.utils.base.ObjectUtils;
import com.authstr.ff.utils.base.ReflectionUtils;
import com.authstr.ff.utils.base.StringUtils;
import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.exception.ErrorException;
import com.authstr.ff.utils.model.AbstractModel;
import com.authstr.ff.utils.page.AbstractPage;
import com.authstr.ff.utils.page.Page;
import com.authstr.ff.utils.page.QueryCommonPage;



public class AbstractDao implements InterfaceDao{
	//通过注入的EntityManager来获取数据库操作对象session
	@PersistenceContext
    private EntityManager entityManager;

	
	 /**
	  * 获取数据库操作对象,,通过session,this.entityManager.unwrap(Session.class);
	 * @return org.hibernate.Session对象
	 * @time 2018年9月17日09:11:26
	 *  @author authstr
	 */
	@Override
	public Session getSession() {
	        return (Session)this.entityManager.unwrap(Session.class);
	  }

    /**
     *  在进行多个实体的操作时,需要调用该方法
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
    	int num = 0;
    	for(int i=0;i<entityList.size();i++){
			update(entityList.get(i));
			//每20次刷新一下session
			if(i%20==0){
				this.flushSession();
			}
			num=i;
        } 
    	return num;
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
     * 通过id删除一个entiy
     * 删除一个entity对象
     * @time 2018年11月13日14:04:43
     * @author authstr
     */
    @Override
    public int remove(Class clazz, Serializable id) {
        StringBuffer qlstring = new StringBuffer();
    	String tableName=ReflectionUtils.getEntityTableName(clazz);
		if(tableName==null) tableName=clazz.getClass().getName();
        qlstring.append(" delete from " +tableName);
        qlstring.append("where id = :id ");
        Map<String,Object> para=new HashMap<String,Object>();
        para.put("id", id);
        return this.executeSQl(qlstring.toString(), para);
    }
    
    
   
    /**
     * 删除多个entity
     * @param clazz
     * @param ids
     * @return
     * @time 2018年11月13日 上午11:46:34
     * @author authstr
     */
    @Override
    public int removeIds(Class clazz, Serializable[] ids) {
        StringBuffer qlstring = new StringBuffer();
    	String tableName=ReflectionUtils.getEntityTableName(clazz);
		if(tableName==null) tableName=clazz.getClass().getName();
        qlstring.append(" delete from " +tableName);
        qlstring.append(" where id in(:ids) ");
        Map<String,Object> para=new HashMap<String,Object>();
        para.put("ids", ids);
        return this.executeSQl(qlstring.toString(), para);
    }
    
    /**
     * 执行sql语句,对数据库进行操作(map参数)
     * @param qlstring
     * @param kv
     * @return
     * @time 2018年11月13日 上午11:37:44
     * @author authstr
     */
    public int executeSQl(String qlstring,Map<String,Object> kv){
    	NativeQuery query=getSession().createNativeQuery(qlstring);
    	setQueryParameters(query, kv);
    	 return query.executeUpdate();
    }
    
    /**
     * 执行sql语句,对数据库进行操作(value参数)
     * @param qlstring
     * @param value
     * @return
     * @time 2018年11月13日 上午11:39:14
     * @author authstr
     */
    public int executeSQl(String qlstring,Object[] value){
    	NativeQuery query=getSession().createNativeQuery(qlstring);
    	setQueryParameters(query, value);
    	 return query.executeUpdate();
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
        if (value instanceof Collection) {//如果值是集合类型的对象,如,map,set,list,强转并通过list设置
            query.setParameterList(paramName, (Collection)value);
        } else if (value instanceof Object[]) {//如果是数组,也通过list设置
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
	            if (!ObjectUtils.isExist(values)) return query;//数组为空.直接返回
	            for(int i=0;i<values.length;i++){
	            	query.setParameter(i + 1, values[i]);
                }
	            return query;
	    }
	
	/**
	 * 通过两个数组设置query对象的参数
	 * @param query 要操作的query对象
	 * @param paramNames 数组,要替换的sql语句中的占位字符名称,如 :temp
	 * @param values 数组,要替换成的值
	 * @return 被操作的query对象
	 * @time 2018年9月17日10:37:32
	 * @author authstr
	 */
	public Query setQueryParameters(Query query,String[] paramNames,Object[] values){
		if(!ObjectUtils.isArrayExist(paramNames)){//字段名称为空,视为使用”?”来定义参数位置
			return setQueryParameters(query,values);
		}
		Assert.isTrue(paramNames.length==values.length, 
				"参数长度不一致,parm["+paramNames.length+"],value["+values.length+"]",true);//参数长度验证
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
		if(!ObjectUtils.isMapExist(kv))return  query;//map不存在
	    for (Map.Entry<String, Object> m : kv.entrySet()) {//遍历map
             this.setKeyValue(query, m.getKey(), m.getValue());
         }
		return query;
	};
	
	/**
	 * 根据实体查询一条数据
	 * @see #queryOneByEntity(Object, String[])
	 * @param entity entity 要解析的实体类
	 * @param fields 要作为where限制条件的属性名称,属性值自动从实体中获取
	 * @return 查询结果
	 * @time 2018年9月17日22:37:52
	 * @author authstr
	 */
	@Override
	public <T> T queryOneByEntity(T entity,String fields){
		return queryOneByEntity(entity,new String[]{fields});
	}
	
	/**
	 * 根据实体查询一条数据
	 * @see #queryByEntity(Object, String[])
	 * @see #queryByEntity(Object)
	 * @param entity 要解析的实体类
	 * @param fields 要作为where限制条件的属性名称,属性值自动从实体中获取
	 * @return 查询结果
	 * @time 2018年9月17日22:31:25
	 * @author authstr
	 */
	@Override
	public <T> T queryOneByEntity(T entity,String[] fields){
		List<T> res=queryByEntity(entity,fields);
		if(res==null||res.size()==0)return null;
		System.out.println(res.get(0).getClass().getName());
		return res.get(0);
	}
	
	/**
	 * 根据一个实体类构建简单的sql查询语句;     表名为该实体类映射的表,where限制字段为实体里值非空的属性,where限制的值为实体该属性的值,查询返回为"*";
	 * 注意! 如果实体内有short,char,boolean类型的属性,会被加到限制条件中
	 * @param entity 要解析的实体类
	 * @return 查询结果
	 * @time 2018年9月17日18:09:25
	 * @author authstr
	 */
	@Override
	public <T> List<T> queryByEntity(T entity){
		Map<String,Object> pv=ReflectionUtils.getFieldAndValue(entity);//获取实体类中已经存在的属性值
		return queryByEntity(entity,pv);
	}
	
	/**
	 *  根据一个实体类和一些属性名称构建简单的sql查询语句; 
	 * @param entity 要解析的实体类
	 * @param fields 要作为where限制条件的属性名称,属性值自动从实体中获取
	 * @return 查询结果list
	 * @time 2018年9月17日20:37:01
	 * @author authstr
	 */ 
	@Override
	public <T> List<T> queryByEntity(T entity,String[] fields){
		Map<String,Object> pv =new HashMap<String, Object>();
		for(int i=0;i<fields.length;i++){//遍历,从实体中取出对应属性的值
			Object val=ReflectionUtils.executeGetMethod(entity, fields[i]);//执行对应属性的get方法
			Assert.isTrue(val!=null, 
					"指定的属性["+fields[i]+"],未能在["+entity.getClass().getName()+"]类型中get到值!",true);//如果没有get到值,抛出一个内部异常
			pv.put(fields[i], val);//将值保存
        }
		return queryByEntity(entity,pv);
	}
	
	public <T> List<T> queryByEntity(T entity,Map<String,Object> pv){
		String[] fields=new String[pv.size()];
		Object[] values=new Object[pv.size()];
		int i=0;
		for (Entry<String, Object> entry : pv.entrySet()) {//遍历
			fields[i]= entry.getKey();
			values[i]= entry.getValue();
		}
		 return queryByEntity(entity,fields,values);
	}
	
	public <T> List<T> queryByEntity(T entity,String[] fields,Object[] values){
		String tableName=ReflectionUtils.getEntityTableName(entity);//获取实体对应的表名
		Assert.isTrue(StringUtils.hasText(tableName),
				"该实体类["+entity.getClass().getSimpleName()+"]没有设置表映射!",true);//没获取到表名,抛出一个内部异常
		 return queryByEntity(tableName,fields,values,"*",entity.getClass());
	}
	
	/**
	 * 根据相关参数构建简单的sql语句进行查询(参数重写)
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
	 *通过参数,参数值,返回类型执行查询语句(执行方法)
	 * @param sql
	 * @param fields
	 * @param values
	 * @param returnType
	 * @return
	 * @see com.authstr.ff.utils.web.dao.InterfaceDao#getBySQL(java.lang.String, java.lang.String[], java.lang.Object[], java.lang.Class)
	 */
	@Override
	public  <T> List<T> getBySQL(String sql,String[] fields,Object[] values,Class<T> returnType){
		Query query=createQuery(sql.toString(),new AbstractPage(),returnType);//获取query对象
		setQueryParameters(query,fields,values);//设置参数
		List li=query.list();
		List res = converListToModel(returnType, li);
		return res;
	}
	
	/**
	 * 通过参数值和返回类型执行查询语句(参数重写)
	 * @param qlstring
	 * @param values
	 * @param returnType
	 * @return
	 * @time 2018年10月30日 下午5:04:15
	 * @author authstr
	 */
	public  <T> List<T>  getByPropertySql(String qlstring, Object[] values, Class<T> returnType) {
        return getBySQL(qlstring, null,values, returnType);
    }
	
	/**
	 * 创建Query对象
	 * @param sql
	 * @param returnType
	 * @return
	 * @time 2018年9月25日11:43:47
	 * @author authstr
	 */
	public Query createQuery(String sql,Page pageModel,Class returnType){
		Assert.isTrue(StringUtils.hasText(sql));//sql语句是否存在
		NativeQuery query= this.getSession().createNativeQuery(sql);
		//设置返回值类型
		query.setResultTransformer(AliasToEntityHashMapResultTransformer.INSTANCE);
		//设置分页
		query.setFirstResult(pageModel.getFirst());//设置开始行
		query.setMaxResults(pageModel.getRows());//设置数据条数
//		if(returnType != null ){//设置返回值类型
//			query.addEntity(returnType);
//		}
//		query.setResultTransformer(Transformers.aliasToBean(clazz) );
//		 new AliasToEntityHashMapResultTransformer();
//		query.setResultTransformer(new AliasToEntityMapResultTransformer();
		return query;
	}
	
	/**
	 * 查询一页数据(参数重写)
	 * @param qlstring
	 * @param paramAndValue
	 * @param page
	 * @param returnType
	 * @return
	 * @time 2018年10月30日 下午4:46:22
	 * @author authstr
	 */
	public Page queryByParamAndValueSqlMap(String qlstring, Map<String, Object> paramAndValue, QueryCommonPage page, Class returnType){
		AbstractPage p=new AbstractPage();
        p.setRows(Integer.valueOf(page.getRows() == null ? 20 : page.getRows()));
        p.setPage(page.getPage());
        return queryByParamAndValue(qlstring, paramAndValue,p, returnType);
	}
	
	/**
	 * 查询一页数据
	 * @param qlstring
	 * @param paramAndValue
	 * @param page
	 * @return
	 * @time 2018年10月30日 下午4:49:59
	 * @author authstr
	 */
	public Page queryByParamAndValueSqlMap(String qlstring, Map<String, Object> paramAndValue, QueryCommonPage page){
		return queryByParamAndValueSqlMap(qlstring,paramAndValue,page,Map.class);
	}
		
	/**
	 * 查询一页数据(实际执行)
	 * @param qlstring sql语句
	 * @param paramAndValue 参数和参数值
	 * @param page 分页对象
	 * @param returnType 返回值
	 * @return 查询处封装好的分页对象
	 * @time 2018年10月30日 下午4:33:39
	 * @author authstr
	 */
	public Page queryByParamAndValue(String qlstring, Map<String, Object> paramAndValue, Page page, Class returnType){
		Query query=createQuery(qlstring,page,returnType);//获取query对象
		setQueryParameters(query,paramAndValue);//设置参数
		createRecord(page,returnType,query);//获取查询结果
		page.setTotal(getTotalCountByParamAndValue(qlstring,paramAndValue));
		return page;
	}
	
	  /**
	 * 获取查询结果总数
	 * @param qlstring sql语句
	 * @param kv 参数与参数值
	 * @return 结果总数
	 * @time 2018年10月30日 下午4:38:38
	 * @author authstr
	 */
	public Integer getTotalCountByParamAndValue(String qlstring, Map<String, Object> kv) {
	        Query query = null;
	        String sql = " select count(1) AS num from ( " + qlstring + " ) a";
            query = this.getSession().createNativeQuery(sql);
          //设置返回值类型
    		query.setResultTransformer(AliasToEntityHashMapResultTransformer.INSTANCE);
	        setQueryParameters(query, kv);
	        List<Map> li = query.list();
	        if(li == null || li.isEmpty() )return 0;
	        //获取返回值
	        Object Onum=li.get(0).get("num");
	        Integer res=0;
	        //如果返回值是大整数
	        if(Onum instanceof BigInteger){
	        	//转换为int
	        	BigInteger temp=(BigInteger) Onum;
	        	res= temp.intValue();
	        }else{
	        	res=(Integer) Onum;
	        }
            return res;
	    }
	
	 /**
	 * 设置分页查询结果数据
	 * @param pageModel 分页对象
	 * @param returnType 返回值类型
	 * @param query query对象
	 * @time 2018年10月15日 上午11:07:42
	 * @author authstr
	 */
	private void createRecord(Page pageModel, Class returnType, Query query) {
		 List li=query.list();
		 if(returnType == null||Map.class.equals(returnType) || TreeMap.class.equals(returnType) || HashMap.class.equals(returnType) || LinkedHashMap.class.equals(returnType)){
			 pageModel.setRecord(li);
		 }else{
			 throw new ErrorException("分页查询结果返回值类型设置不正确,类型为["+returnType.getName()+"]");
		 }
	 }
	
	/**
	 * 将map的集合,转换为指定对象的集合
	 * @param clazz
	 * @param list
	 * @return
	 * @time 2018年10月15日 上午11:35:53
	 * @author authstr
	 */
	public <T> List converListToModel(Class<T> clazz, List<Map> list){
		 T bean = null;  
		if (clazz == null || HashMap.class.equals(clazz) || Map.class.equals(clazz))  return list;//检查类型
		List<T> res=new ArrayList(list.size());  
		if(!ObjectUtils.isExist(list))return res;//检查list
		for(Map map:list){
			try {
				bean = clazz.newInstance();
			    BeanUtils.populate(bean, map);
			    res.add(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
		
		return res;
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
		if(tableName==null) tableName=model.getClass().getName();
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
		StringBuffer qlstring= new StringBuffer();
		List<Object> values =new ArrayList<>();//参数值集合
		//构建查询语句
		qlstring.append("select count(*) num from " + tableName + " a where 1=1 ");
		for(int i=0;i<field.length;i++){//遍历字段,拼接查询条件
			String fieldName=field[i].trim();
			if(StringUtils.hasText(fieldName)){
				  Object propertyValue = ReflectionUtils.getProperty(model,fieldName);//获取属性值
				  qlstring.append(" and a." + fieldName + "=?");
				  values.add(propertyValue);//记录属性值
			}
        }
		//判断是否存在id
		if(model.getId()!=null){
			 qlstring.append(" and a.id <> ?");//id不等于当前id
			 values.add(model.getId());//记录属性值
		}
		//执行查询
		List<Map<String,Object>>  list=getByPropertySql(qlstring.toString(), values.toArray(), null);
		//if(list==null)return false;
		Integer num=Numberutils.toInteger(list.get(0).get("num"));
		if(num!=null&&num==0)return true;
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
        if (id == null || StringUtils.notText(id.toString()))  return null;//非空验证
        if (fields == null || fields.length <= 0)  return this.getSession().get(clazz, id);//查询一个对象
        //拼接sql,查询指定字段
        StringBuffer qlstring = new StringBuffer();
        qlstring.append(" select  ");
        qlstring.append(StringUtils.arrayToString(fields));
        qlstring.append(" from " + clazz.getName());
        qlstring.append(" where id=?");
        List<T> li= getByPropertySql(qlstring.toString(),new Object[]{id},clazz);
        return li==null||li.isEmpty()?null :li.get(0);
    }
	
	
	
}
