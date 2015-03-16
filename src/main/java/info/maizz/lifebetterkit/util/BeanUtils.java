package info.maizz.lifebetterkit.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * java bean 一些 操作
 *
 * @author <a href="stick.goal@163.com">lucas</a>
 *
 */
public class BeanUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
	
	/**
	 * 将Map中的值根据属性名称给传入的实例赋值，忽略指定的属性
	 * @param instance
	 * @param map
	 * @param beanClass
	 * @param properties
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	public static <T> T toBeanWithIgnore(T instance, Map<String, Object> map, Class<T> beanClass,
											String... properties) throws IllegalAccessException,
																	IllegalArgumentException,
																	InvocationTargetException,
																	IntrospectionException {
		return toBean(instance, map, beanClass, true, properties);
	}
	
	/**
	 * 将Map转换成Bean，根据属性名称赋值，忽略指定的属性
	 * @param map
	 * @param beanClass
	 * @param properties
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	public static <T> T toBeanWithIgnore(Map<String, Object> map, Class<T> beanClass,
											String... properties) throws IllegalAccessException,
																	IllegalArgumentException,
																	InvocationTargetException,
																	IntrospectionException {
		return toBean(null, map, beanClass, true, properties);
	}
	
	/**
	 * 使用指定的map中指定的属性值给传入的beanClass实例赋值
	 * @param instance
	 * @param map
	 * @param beanClass
	 * @param properties
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	public static <T> T toBeanWithin(T instance, Map<String, Object> map, Class<T> beanClass,
										String... properties) throws IllegalAccessException,
																IllegalArgumentException,
																InvocationTargetException,
																IntrospectionException {
		return toBean(instance, map, beanClass, false, properties);
	}
	
	/**
	 * 使用指定的map中指定的属性值创建一个beanClass实例并赋值
	 * @param map
	 * @param beanClass
	 * @param properties
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	public static <T> T toBeanWithin(Map<String, Object> map, Class<T> beanClass,
										String... properties) throws IllegalAccessException,
																IllegalArgumentException,
																InvocationTargetException,
																IntrospectionException {
		return toBean(null, map, beanClass, false, properties);
	}
	
	/**
	 * 将Map转换成Bean，根据属性名称赋值
	 * 
	 * @param map 原始数据map
	 * @param beanClass 指定的bean类
	 * @param properties 需要忽略的属性
	 * @param instance 使用指定实例不新建
	 * @param ignore true表示忽略，false表示使用指定的properties来赋值
	 * @param properties
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T toBean(T instance, Map<String, Object> map, Class<T> beanClass,
								boolean ignore, String... properties)
																		throws IntrospectionException,
																		IllegalAccessException,
																		IllegalArgumentException,
																		InvocationTargetException {
		T t = null;
		List<String> propList = Lists.newArrayList();
		if (properties != null) {
			propList = Arrays.asList(properties);
		}
		
		//创建一个类的实例
		try {
			t = instance == null ? beanClass.newInstance() : instance;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("指定的类无法实例化");
		}
		
		//转换
		BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor p : propertyDescriptors) {
			String propertyName = p.getName();
			
			if (properties == null) {
				Method writeMethod = p.getWriteMethod();
				Object propertyValue = map.get(propertyName);
				if (writeMethod != null && propertyValue != null)
					writeMethod.invoke(t, propertyValue);
			} else {
				if ((ignore && !propList.contains(propertyName))
					|| (!ignore && propList.contains(propertyName))) {
					Method writeMethod = p.getWriteMethod();
					Object propertyValue = map.get(propertyName);
					if (writeMethod != null && propertyValue != null)
						writeMethod.invoke(t, propertyValue);
				}
			}
			
		}
		
		return t;
	}
	
}
