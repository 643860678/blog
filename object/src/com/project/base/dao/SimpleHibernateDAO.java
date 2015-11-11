/**
 * 
 */
package com.project.base.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author xiaokai create on 2014-6-15
 * @param <T>
 */

@Repository
public class SimpleHibernateDAO extends AbstraceHibernateDAO {

	public void save(Object entity) {
		this.getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 根据ID得到已该Id为主键的Object
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 根据ID进行对应的实体删除
	 * 
	 * @param entityClazz
	 *            对应的实体类
	 * @param id
	 *            主键
	 * @throws
	 */
	public <T> void deleteById(Class<T> entityClazz, Serializable id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(entityClazz, id));
	}

	/**
	 * 获得单体信息的列表相关
	 * 
	 * @param @param firstResult
	 * @param @param maxResults
	 * @param @return 设定文件
	 * @return List<?> 返回类型
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getEntityPage(Class<T> entityClazz,
			final int firstResult, final int maxResults) {
		return super.getEntities(entityClazz, firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> entityClazz) {
		return super.getAllEntities(entityClazz);
	}

	// =======================DataSix==============================
}
