package com.project.base.dao;

/**
 * 
 */

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 
 */

public class AbstraceHibernateDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 根据HQL进行批量删除
	 * 
	 * @Title: deleteByHql
	 * @param @param hql 设定文件
	 * @return void 返回类型
	 */
	public void deleteByHql(String hql, Object[] objs) {
		this._execute(hql, objs);
	}

	/**
	 * 根据HQL进行批量删除
	 * 
	 * @Title: deleteByHql
	 * @param @param hql 设定文件
	 * @return void 返回类型
	 */
	public void deleteByHql(String hql) {
		this.deleteByHql(hql, null);
	}

	/**
	 * 执行HQL语句、其中包括删除、更新、等等直接可以用HQL进行操作的语句
	 * 
	 * @param hql
	 * @param objs
	 */
	public void executeHQL(String hql, Object[] objs) {
		this._execute(hql, objs);
	}

	/**
	 * 基本为工具类 随时能够得到相关列表
	 * 
	 * @param hql
	 * @return
	 */
	public List<?> getList(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	public List<?> getList(String hql, Object[] objs) {
		return this.getHibernateTemplate().find(hql, objs);
	}

	public List<?> getList(String hql, int firstResult, int maxResults) {
		return this.getList(hql, null, firstResult, maxResults);
	}

	public List<?> getList(String hql, Object[] values, int firstResult,
			int maxResults) {
		return this._getList(hql, values, null, firstResult, maxResults);
	}


	public void _execute(final String hql, final Object[] objs) {
		getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (objs != null) {
					for (int i = 0; i < objs.length; i++) {
						query.setParameter(i, objs[i]);
					}
				}
				query.executeUpdate();
				return null;
			}
		});
	}
	
	//用于事务
	public boolean _tranExecuteSave(final String []sql,final Object[] f2){
		final boolean fl=true;
		getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Transaction tx=session.beginTransaction();
				tx.begin();
				Query query=null;
				try {
					for (int i = 0; i < sql.length; i++) {
						query=session.createQuery(sql[i]);
						query.executeUpdate();
					}
					tx.commit();
					f2[0] = (Object)1;
				} catch (Exception e) {
					tx.rollback();
					f2[0] = (Object)0;
				}
				return null;
			}
		});
		
		System.out.println(f2[0]);
		if((Integer)f2[0]==1){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public static void main(String[] args) {
		/*AbstraceHibernateDAO ad=new AbstraceHibernateDAO();
		String []sql={"insert into test(name) values('李四')","insert into test(name) values('张三')","insert into test(name) values('王五')"};
		ad._tranExecuteSave(sql, new Object[]{1});
		System.out.println("sd");*/
		AbstraceHibernateDAO ad=new AbstraceHibernateDAO();
		/*String []sql={"insert into test(name) values('李四')","insert into test(name) values('张三')","insert into test(name) values('王五')"};
		ad._tranExecuteSave(sql, new Object[]{1});
		System.out.println("sd");*/
		String sql="insert into test(name) values(?)";
		ad._execute(sql, new Object[]{"张三"});
	}
	
	private List<?> _getList(final String hql, final Object[] values,
			final Type[] types, final int firstResult, final int maxResults) {
		List<?> list = getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						/** 进行判断 **/
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								if (types != null) {
									query.setParameter(i, values[i], types[i]);
								} else {
									query.setParameter(i, values[i]);
								}
							}
						}
						query.setFirstResult(firstResult);
						query.setMaxResults(maxResults);
						query.setCacheable(true);
						List<?> list = query.list();
						return list;
					}
				});
		return list;
	}

	// 如果不是查询缓存设置一下,然后返回hibernateTemplate
	protected HibernateTemplate getHibernateTemplate() {
		if (!this.hibernateTemplate.isCacheQueries()) {
			this.hibernateTemplate.setCacheQueries(true);
		}
		return this.hibernateTemplate;
	}

	@SuppressWarnings("rawtypes")
	protected List getEntities(final Class<?> clazz, final int firstResult,
			final int maxResults) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(clazz);
				criteria.setFirstResult(firstResult);
				criteria.setMaxResults(maxResults);
				criteria.setCacheable(true);
				List<?> list = criteria.list();
				return list;
			}
		});
		return list;
	}

	@SuppressWarnings({ "rawtypes" })
	protected List getAllEntities(final Class clazz) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(clazz);
				List list = criteria.list();
				return list;
			}
		});
		return list;
	}

	/**
	 * 获取符合条件的所有记录
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public List findByCriteria(final DetachedCriteria detachedCriteria) {
		return (List) getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return criteria.list();
					}
				});
	}

	/**
	 * 获取符合条件的记录个数
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Number count = (Number) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				});
		return count.intValue();
	}

}
