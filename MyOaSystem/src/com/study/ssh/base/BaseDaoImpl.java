package com.study.ssh.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.util.LoggerManager;

/**
 * BaseDao的实现类，对增删改查进行实现
 * @author anbang
 * @param <T>
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
		LoggerManager.getClassInfo(clazz);
	}
	
	/**
	 * 得到当前可用的Session
	 * @return 当前可用的Session对象
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
		LoggerManager.printInfo(clazz, "成功添加一条记录");
	}

	@Override
	public void delete(Long id) {
		Object object = getById(id);
		getSession().delete(object);
		LoggerManager.printInfo(clazz, "成功删除一条记录");
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
		LoggerManager.printInfo(clazz, "已更新");
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
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	@Override
	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

}
