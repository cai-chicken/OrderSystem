package com.mythesis.ssh.util;

import java.util.ArrayList;
import java.util.List;

import com.mythesis.ssh.base.BaseDao;
import com.mythesis.ssh.model.PageBean;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author anbang
 * @description 用以辅助拼接hql
 * @date 2016年1月3日 下午2:37:34
 */
public class QueryHelper {
	/** from子句 */
	private String from;
	/** where子句 */
	private String where = "";
	/** orderBy子句 */
	private String orderBy = "";
	/** 参数列表 */
	private List<Object> paramList = new ArrayList<Object>();

	/**
	 * 为了保证from子句是必须有的
	 * 
	 * @param clazz
	 *            hql中要查找的对象
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class clazz, String alias) {
		from = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 拼接where子句
	 * 
	 * @param condition
	 *            条件
	 * @param params
	 *            参数，因为参数的数量是不确定的
	 * @return
	 */
	public QueryHelper addWhereCondition(String condition, Object... params) {
		if (where.length() == 0) {
			where = " WHERE " + condition;
		} else {
			where += " AND " + condition;
		}
		if (params != null) {
			for (Object p : params) {
				paramList.add(p);
			}
		}
		return this;
	}
	

	/**
	 * 如果第一个参数为true，则拼接Where子句
	 * 
	 * @param append
	 *            因为where子句是可以没有的
	 * @param condition
	 * @param params
	 */
	public QueryHelper addWhereCondition(boolean append, String condition, Object... params) {
		if (append) {
			addWhereCondition(condition, params);
		}
		return this;
	}

	/**
	 * 拼接OrderBy子句
	 * 
	 * @param propertyName
	 *            参与排序的属性名
	 * @param asc
	 *            true表示升序，false表示降序
	 */
	public QueryHelper addOrderProperty(String propertyName, boolean asc) {
		if (orderBy.length() == 0) {
			orderBy = " ORDER BY " + propertyName + (asc ? " ASC" : " DESC");
		} else {
			orderBy += ", " + propertyName + (asc ? " ASC" : " DESC");
		}
		return this;
	}

	/**
	 * 如果第一个参数为true，则拼接OrderBy子句
	 * 
	 * @param append
	 * @param propertyName
	 * @param asc
	 */
	public QueryHelper addOrderProperty(boolean append, String propertyName, boolean asc) {
		if (append) {
			addOrderProperty(propertyName, asc);
		}
		return this;
	}

	/**
	 * 获取生成的用于查询数据列表的HQL语句
	 * 
	 * @return
	 */
	public String getListQueryHql() {
		return from + where + orderBy;
	}

	/**
	 * 获取生成的用于查询总记录数的HQL语句
	 * 
	 * @return
	 */
	public String getCountQueryHql() {
		return "SELECT COUNT(*) " + from + where;
	}

	/**
	 * 获取HQL中的参数值列表
	 * 
	 * @return
	 */
	public List<Object> getParamList() {
		return paramList;
	}

	/**
	 * 查询分页信息，并放到值栈栈顶
	 * 
	 * @param service
	 * @param pageNum
	 * @param pageSize
	 */
	public void preparePageBean(BaseDao<?> service, int pageNum, int pageSize) {
		PageBean pageBean = service.getPageBean(pageNum, pageSize, this);
		ActionContext.getContext().put("pageBean", pageBean);
	}
}
