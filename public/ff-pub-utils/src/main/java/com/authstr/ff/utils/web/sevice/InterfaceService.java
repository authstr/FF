package com.authstr.ff.utils.web.sevice;

import com.authstr.ff.utils.model.AbstractModel;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public interface InterfaceService {


    <T> T get(Class<T> clazz, Serializable id);

    <T> T get(Class<T> clazz, Serializable id, String[] fields);

    <T extends AbstractModel> boolean isUnique(T entity, String[] fields);


    @Transactional
    <T extends AbstractModel> Serializable save(T entity);

    @Transactional
    int saveList(List<? extends AbstractModel> listEntity);


    @Transactional
    <T extends AbstractModel> void coverUpdata(T entity);

    @Transactional
    <T extends AbstractModel> void updata(T entity);

    @Transactional
    void updateList(List<? extends AbstractModel> listEntity);

    @Transactional
    <T extends AbstractModel> void updata(T entity, boolean isCopy);

    int remove(Class clazz, Serializable id);

    int removeIds(Class clazz, Serializable[] ids);
}
