package com.study.ssh.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 所有需要实现ModelDriven接口的都必须继承该Action
 * 
 * @author Administrator
 *
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
