package com.mythesis.ssh.util;

/**
 * @author anbang
 * @description 字符串工具类 
 * @date 2016年1月10日 下午6:41:32
 */
public class StringUtil {
	/**
	 * 判断字符串是否为空串
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if ("".equals(str) || str == null || str.equals("") || str.equals(null)) {
			return true;
		}
		return false;
	}
}
