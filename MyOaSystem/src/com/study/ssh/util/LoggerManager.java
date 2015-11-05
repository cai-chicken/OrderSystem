package com.study.ssh.util;

import org.apache.log4j.Logger;

/**
 * 日志管理类
 * @author anbang
 * @date 15-11-5
 *
 */
public class LoggerManager {
	
	private static Logger logger ;
	
	/**
	 * 得到Logger对象
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class clazz) {
		logger = Logger.getLogger(clazz);
		return logger;
	}
	
	/**
	 * 得到当前类的信息
	 * @param clazz
	 */
	public static void getClassInfo (Class clazz) {
		if (logger == null) {
			logger = getLogger(clazz);
		}
		logger.info("当前操作的类是：" + clazz.getSimpleName());
	}
	
	/**
	 * 打印指定的信息
	 * @param clazz
	 * @param info
	 */
	public static void printInfo(Class clazz, String info) {
		if (logger == null) {
			logger = getLogger(clazz);
		}
		logger.info(info);
	}
}

