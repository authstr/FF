package com.authstr.ff.utils.web.sevice;

import com.authstr.ff.utils.model.AbstractModel;

import java.io.Serializable;
import java.util.List;

public interface InterfaceService {


    <T> T get(Class<T> clazz, Serializable id);

    <T> T get(Class<T> clazz, Serializable id, String[] fields);

    <T extends AbstractModel> boolean isUnique(T entity, String[] fields);

    Serializable save(Object entity);

    int saveList(List listEntity);

    void coverUpdata(Object entity);

    void updata(Object entity);

    void updateList(List listEntity);

    void updata(Object entity, boolean isCopy);

    int remove(Class clazz, Serializable id);

    int removeIds(Class clazz, Serializable[] ids);
}
