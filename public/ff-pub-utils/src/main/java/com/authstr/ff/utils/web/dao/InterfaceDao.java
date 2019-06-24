package com.authstr.ff.utils.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.authstr.ff.utils.model.AbstractModel;

public interface InterfaceDao {

	Session getSession();


	<T> List<T> getBySQL(String sql, String[] fields, Object[] values, Class<T> returnType);


	void flushSession();

	 <T extends AbstractModel> boolean isUnique(T model, String...field);

	<T> T get(Class<T> clazz, Serializable id, String[] fields);

	<T> T get(Class<T> clazz, Serializable id);


	<T extends AbstractModel> Serializable save(T entity);

	<T extends AbstractModel> Serializable save(T entity, Boolean isValidation);

	abstract List<Serializable> saveList(List<? extends AbstractModel> entityList);


	 
	 public Integer remove(Class clazz, Serializable id);


	<T extends AbstractModel> void update(T entity);


	<T extends AbstractModel> void update(T entity, Boolean isValidation);

	Integer updateList(List<? extends AbstractModel> entityList);

	public void remove(Object entity);
	 public Integer removeIds(Class clazz, Serializable[] ids);


	

}
