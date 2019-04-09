package com.authstr.ff.utils.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.authstr.ff.utils.model.AbstractModel;

public interface InterfaceDao {

	Session getSession();

	Serializable save(Object entity);



	<T> List<T> getBySQL(String sql, String[] fields, Object[] values, Class<T> returnType);

	void update(Object entity);

	int updateList(List entityList);

	void flushSession();

	public <T extends AbstractModel> boolean isUnique(T model, String...field);

	<T> T get(Class<T> clazz, Serializable id, String[] fields);

	<T> T get(Class<T> clazz, Serializable id);

	List<Serializable> saveList(List entityList);


	 
	 public int remove(Class clazz, Serializable id);

	 public void remove(Object entity);
	 public int removeIds(Class clazz, Serializable[] ids);


	

}
