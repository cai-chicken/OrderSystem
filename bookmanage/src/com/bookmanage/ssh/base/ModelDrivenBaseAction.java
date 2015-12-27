package com.bookmanage.ssh.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;


/**
 * @author anbang
 * @description @param <T>所有需要实现ModelDriven接口的都必须继承该Action
 * @date 2015年12月26日 上午12:10:24
 */
@SuppressWarnings("serial")
public class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T>{
	//ModelDriven接口会将该model放入到对象栈的栈顶
	protected T model;

	public ModelDrivenBaseAction() {
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];
			this.model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		return model;
	}
}
