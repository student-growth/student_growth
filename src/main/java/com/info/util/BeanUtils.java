package com.info.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class BeanUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/**
	 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtils.applicationContext = applicationContext;
	}

	/**
	 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
//		Map<String, T> map = applicationContext.getBeansOfType(clazz);
//		return (T) map.values().<T>toArray()[0];
		return (T) applicationContext.getBean(clazz);
	}

	/**
	 清除applicationContext静态变量.
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}

	private static void checkApplicationContext() {
		Assert.notNull(applicationContext, "applicationContext未注入,请在applicationContext.xml中定义BeanUtil");
	}

	//--------自定义工具方法------//

	public static <T> T transferBean(Object bean, Class<T> clazz) {
		T t = null;
		try {
			String str = JSONObject.toJSONString(bean);
			t = JSON.parseObject(str, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> List<T> transferBeanList(List list, Class<T> clazz) {
		List<T> result = new ArrayList<>();
		if (list == null || list.isEmpty()) {
			return result;
		}
		for (Object obj : list) {
			result.add(transferBean(obj, clazz));
		}
		return result;
	}
}
