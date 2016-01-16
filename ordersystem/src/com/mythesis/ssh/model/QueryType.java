package com.mythesis.ssh.model;

/**
 * @author anbang
 * @description 模糊查询时使用
 * @date 2016年1月16日 上午11:24:37
 */
public class QueryType {
	private String param;// 要查询的参数
	private String value;// 对应查询参数的值

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
