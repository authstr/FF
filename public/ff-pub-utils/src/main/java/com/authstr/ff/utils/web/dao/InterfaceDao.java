package com.authstr.ff.utils.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.authstr.ff.utils.model.AbstractModel;

public interface InterfaceDao {

	Session getSession();

	Serializable save(Object entity);

	<T> T queryOneByEntity(T entity, String[] fields);

	<T> List<T> queryByEntity(T entity);

	<T> List<T> queryByEntity(T entity, String[] fields);

	<T> List<T> getBySQL(String sql, String[] fields, Object[] values, Class<T> returnType);

	void update(Object entity);

	int updateList(List entityList);

	void flushSession();

	public <T extends AbstractModel> boolean isUnique(T model, String...field);

	public <T> T get(Class<T> clazz, Serializable id, String[] fields);

	 public <T> T get(Class<T> clazz, Serializable id);

	 public List<Serializable> saveList(List entityList);

	 public <T>  T queryOneByEntity(T entity, String fields);
	 
	 public int remove(Class clazz, Serializable id);

	 public void remove(Object entity);
	 public int removeIds(Class clazz, Serializable[] ids);


	

}
