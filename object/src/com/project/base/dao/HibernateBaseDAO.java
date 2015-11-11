package com.project.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public abstract class HibernateBaseDAO<T> extends AbstraceHibernateDAO {

	/**
	 * 返回操作类的class(子类必须实现)
	 * 
	 * @Title: getReferenceClass
	 */
	public abstract Class<?> getReferenceClass();

	/**
	 * 实体进行保存--返回相应的实体类型
	 * 
	 * @param entity
	 *            带保存的实体类型
	 * @return Object 返回托管的实体
	 */
	public T save(T entity) {
		this.getHibernateTemplate().save(entity);
		return entity;
	}

	/**
	 * 实体进行保存和更新--
	 * 
	 * @param entity
	 *            带保存的实体类型
	 * @return void
	 */
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	/**
	 * 根据ID得到已该Id为主键的Object
	 * 
	 * @param @param id 主键ID
	 * @return T 进行对应主键的类型返回
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getHibernateTemplate().get(getReferenceClass(), id);
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(getReferenceClass(), id);
	}

	/**
	 * 删除对应的实体类型
	 * 
	 * @param @param entity 对应的实体
	 * @throws
	 */
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 根据ID进行对应的实体删除
	 * 
	 * @param @param entity 对应的实体
	 * @throws
	 */
	public void deleteById(Serializable id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().get(getReferenceClass(), id));
	}

	/**
	 * 获得单体信息的列表相关
	 * 
	 * @Title: getEntityPage
	 * @param @param firstResult
	 * @param @param maxResults
	 * @param @return 设定文件
	 * @return List<?> 返回类型
	 */
	@SuppressWarnings("unchecked")
	public List<T> getEntityPage(final int firstResult, final int maxResults) {
		return super.getEntities(getReferenceClass(), firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return super.getAllEntities(getReferenceClass());
	}

	/**
	 * 构造一个离线查询对象
	 * 
	 * @return
	 */
	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(getReferenceClass());
	}


	/**
	 * 获取符合条件的所有记录
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return super.findByCriteria(detachedCriteria);
	}
}
