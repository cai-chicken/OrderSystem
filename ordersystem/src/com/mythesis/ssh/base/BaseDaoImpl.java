package com.mythesis.ssh.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.model.PageBean;
import com.mythesis.ssh.util.QueryHelper;

/**
 * @author anbang
 * @description @param <T>BaseDao的实现类，对增删改查进行实现
 * @date 2016年1月3日 下午2:36:57
 */
@Transactional
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource
	private SessionFactory sessionFactory;

	protected Class<T> clazz = null;

	/**
	 * 用于反射得到真实类型
	 */
	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];
	}

	/**
	 * 得到当前可用的Session
	 * 
	 * @return 当前可用的Session对象
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Long id) {
		Object object = getById(id);
		getSession().delete(object);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}
		return getSession()
				.createQuery(//
						"FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	@Override
	public List<T> findAll() {
		return getSession()
				.createQuery(//
						"FROM " + clazz.getSimpleName())//
				.list();
	}

	@Override
	public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {
		// 参数列表
		List<Object> paramList = queryHelper.getParamList();
		System.out.println("过滤参数---------------->" + paramList.toString());
		// 查询到的数据列表
		Query listQuery = getSession().createQuery(queryHelper.getListQueryHql());
		// 设置参数
		if (paramList != null) {
			for (int i = 0; i < paramList.size(); i++) {
				listQuery.setParameter(i, paramList.get(i));
			}
		}
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		List list = listQuery.list();// 执行查询
		// 查询总记录数量
		Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
		if (paramList != null) { // 设置参数
			for (int i = 0; i < paramList.size(); i++) {
				countQuery.setParameter(i, paramList.get(i));
			}
		}
		Long count = (Long) countQuery.uniqueResult(); // 执行查询
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

}
