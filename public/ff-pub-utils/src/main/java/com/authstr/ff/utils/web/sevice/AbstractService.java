package com.authstr.ff.utils.web.sevice;

import java.io.Serializable;
import java.util.List;

import com.authstr.ff.utils.exception.Assert;
import com.authstr.ff.utils.model.ModelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.authstr.ff.utils.exception.ErrorException;
import com.authstr.ff.utils.model.AbstractModel;
import com.authstr.ff.utils.web.dao.BasicDao;

import javax.transaction.Transactional;

/**
 * 服务层的超类
 * 封装了基础的删改查和查重操作
 * @author authstr
 * 2019年7月5日21:23:48
 */
@Component
public class AbstractService implements InterfaceService{

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	  @Autowired
	  @Qualifier(value="basicDaoImpl")
	  public BasicDao basicDao;
	  
		/**
		* 通过id从数据库获取指定的对象
		* @param clazz
		* @param id
		* @return
		* @time 2018年11月4日 下午4:01:10
		* @author authstr
		*/
		@Override
		public <T> T get(Class<T> clazz, Serializable id){
			return basicDao.get(clazz, id);
		}
	
	
	   /**
	 * 通过id从数据库获取指定对象的指定字段
	 * @param clazz
	 * @param id
	 * @param fields
	 * @return
	 * @time 2018年11月4日 下午4:24:40
	 * @author authstr
	 */
	   @Override

	public <T> T get(Class<T> clazz, Serializable id, String[] fields) {
	   	return basicDao.get(clazz, id,fields);
	   }
	
	/**
	 * 判断一个对象里一些属性的值是否具有唯一性
	 * @Param entity 实体对象
	 * @param fields 字段名称
	 * @return
	 * @time 2018年11月1日 上午11:23:56
	 * @author authstr
	 */
	@Override
    public <T extends AbstractModel> boolean isUnique(T entity, String[] fields) {
        return this.basicDao.isUnique(entity, fields);
    }
    
    /**
     * 将一个对象保存到数据库
     * @param entity
     * @return
     * @time 2018年11月4日 下午4:27:26
     * @author authstr
     */
    @Override
	@Transactional
    public <T extends AbstractModel> Serializable save(T entity) {
        return this.basicDao.save(entity);
    }
    
    /**
     * 保存model集合
     * @param listEntity
     * @return
     * @time 2018年11月13日 上午11:14:34
     * @author authstr
     */
    @Override
	@Transactional
    public int saveList(List<? extends AbstractModel> listEntity) {
        return basicDao.saveList(listEntity).size();
    }

    /**
     * 将一个对象更新到数据库(覆盖)
     * @param entity
     * @time 2018年11月13日 上午11:07:58
     * @author authstr
     */
    @Override
	@Transactional
    public <T extends AbstractModel> void coverUpdata(T entity){
		updata(entity,true);
    }

    /**
     * 将一个对象更新到数据库
     * @param entity
     * @time 2018年11月13日 上午11:07:38
     * @author authstr
     */
    @Override
	@Transactional
    public <T extends AbstractModel> void updata(T entity){
    	updata(entity,false);
    }

    /**
     * 更新model集合
     * @param listEntity
     * @time 2018年11月13日 上午11:22:18
     * @author authstr
     */
    @Override
	@Transactional
    public void updateList(List<? extends AbstractModel> listEntity) {
        basicDao.updateList(listEntity);
    }

    /**
     * 更新
     * @param entity  更新的实体对象
     * @param isCover 是否覆盖更新 true 是，false 否.
     * @param <T>
     */
    @Override
    @Transactional
    public <T extends AbstractModel> void updata(T entity, boolean isCover) {
        if (isCover) {
            //如果是覆盖更新，直接调用更新
            basicDao.update(entity);
        } else {
            //如果不是，先查询，再更新
            Object no = get(entity.getClass(), (entity).getId());
            if (no == null) {
            	throw new ErrorException("该model没有id,无法更新");
            }
            BeanUtils.copyProperties(entity, no);
            basicDao.update((AbstractModel) no);
        }
    }

    /**
     * 保存或更新，更新时为非覆盖更新
     * @param entity 实体对象
     * @return id
     * @time 2018年11月4日 下午4:27:26
     * @author 桔子
     */
    @Override
    @Transactional
    public <T extends AbstractModel> Serializable saveOrUpdate(T entity) {
        if (ModelUtils.isNew(entity)) {
            return this.basicDao.save(entity);
        } else {
            this.updata(entity);
            return entity.getId();
        }
    }

    @Override
	@Transactional
    public int remove(Class clazz, Serializable id) {
        return this.basicDao.remove(clazz, id);
    }

    @Override
	@Transactional
    public int removeIds(Class clazz, Serializable[] ids) {
        return this.basicDao.removeIds(clazz, ids);
    }


	@Override
	@Transactional
	public void remove(Object entity) {
		this.basicDao.remove(entity);
	}

	@Override
	@Transactional
	public void removeList(List<Object> entityList) {
		Assert.isTrue(null != entityList && entityList.size()!=0,"没有要删除的主体对象",true);
		for (Object entity:entityList){
			this.remove(entity);
		}

	}


}
