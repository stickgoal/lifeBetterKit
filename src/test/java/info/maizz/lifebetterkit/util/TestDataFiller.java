/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年1月29日 上午10:25:10 创建
 */
package info.maizz.lifebetterkit.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * 测试数据工具类 <br />
 * <ol>
 * <li>自动使用默认数据填充 使用每种数据类型默认的数据值填充字段。<br>
 * 自动生成一个实例，减少手工填写数据的工作量。暂不支持嵌套的复杂类型，只能手工搞。
 * <p>
 * 用法：
 * </p>
 * <br>
 * <code>
			System.out.println(new TestDataFiller<Application>().fill(Application.class));
 *</code>
 * <p>
 * 输出：
 * </p>
 * <code>
 * 		Application{applicationId=123,applicationNo=20150129170156181436,dog=Dog [nameCard=NameCard [Id=1234567890_1, name=1234567890_0], age=123],memo=备注YOYO\(^o^)/~,status=INIT,userId=12345678901234567890}
 * </code></li>
 *
 * <li>TODO 读取CSV文件，按照字段名称匹配填充 ,绑定部分代码如下：<br>
 * <code>
			Map<String, String> data = ImmutableMap.of("dog.mydogsNameCardWhichNameIsLong.name", "cat",
			"dog.age", "5", "memo", "hahah", "status", "SUCCESS", "userId", "111111111");
			
			System.out.println(new TestDataFiller().bind(Application.class, data));
 *	</code>
 * <p>
 * 输出：
 * </p>
 * <code>
 * 		Application{applicationId=0,applicationNo=null,dog=Dog [nameCard=NameCard [Id=null, name=cat], age=5],memo=hahah,status=SUCCESS,userId=111111111}
 * </code></li>
 *
 * </ol>
 * @author lucas(stick.goal@163.com)
 *
 */
public class TestDataFiller {
	
	private static Logger logger = LoggerFactory.getLogger(TestDataFiller.class);
	
	public static int sameTypeFieldCount = 0;
	
	@SuppressWarnings("rawtypes")
	private static final List ACCEPT_NONPRIMITIVE_CLASSES = ImmutableList.of(String.class,
		Money.class);
	
	/**
	 * 自动生成一个实例，减少手工填写数据的工作量。 默认值请参考 {@link DefaultTestData}
	 * <p>
	 * 用法：
	 * </p>
	 * <code>
			System.out.println(TestDataFiller.fill(Application.class));
	 *</code>
	 * <p>
	 * 输出：
	 * </p>
	 * <code>
	 * 		Application{applicationId=123,applicationNo=20150129170156181436,dog=Dog [nameCard=NameCard [Id=1234567890_1, name=1234567890_0], age=123],memo=备注YOYO\(^o^)/~,status=INIT,userId=12345678901234567890}
	 * </code>
	 * @param clazz 需要填充的实例的类型
	 * @param notFill 不填充的字段
	 * @return 按照默认
	 */
	public static <T> T fill(Class<T> clazz, String... notFill) {
		return new TestDataFiller().fillIt(clazz, notFill);
	}
	
	/**
	 * 自动生成一个实例，减少手工填写数据的工作量。 默认值请参考 {@link DefaultTestData}
	 * <p>
	 * 用法：
	 * </p>
	 * <code>
			System.out.println(new TestDataFiller<Application>().fill(Application.class));
	 *</code>
	 * <p>
	 * 输出：
	 * </p>
	 * <code>
	 * 		Application{applicationId=123,applicationNo=20150129170156181436,dog=Dog [nameCard=NameCard [Id=1234567890_1, name=1234567890_0], age=123],memo=备注YOYO\(^o^)/~,status=INIT,userId=12345678901234567890}
	 * </code>
	 * @param clazz 需要填充的实例的类型
	 * @return 按照默认
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> T fillIt(Class<T> clazz, String... notFill) {
		logger.info("填充{},忽略字段{}", clazz.getCanonicalName(), Arrays.toString(notFill));
		
		T newInstance = null;
		try {
			List<String> notFillMethodNames = toSetterMethodName(notFill);
			newInstance = clazz.newInstance();
			
			Method[] setterMethods = getSetterMethods(clazz);
			
			for (Method m : setterMethods) {
				if (m == null) {
					continue;
				}
				if (!m.isAccessible()) {
					m.setAccessible(true);
				}
				
				if (notFillMethodNames.contains(m.getName())) {
					continue;
				}
				
				invokeByTypeAndName(newInstance, m);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("填充数据失败:" + e.getMessage());
		}
		return newInstance;
	}
	
	/**
	 * 得到指定的 clazz 里所有的写入方法。
	 * 
	 * @param clazz 指定的 Class。
	 * @return 返回对应的写入方法的 Method 数组对象。
	 */
	public static Method[] getSetterMethods(Class<?> clazz) {
		BeanInfo beanInfo;
		Method[] methods = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			methods = new Method[propertyDescriptors.length];
			for (int i = 0; i < methods.length; i++) {
				methods[i] = propertyDescriptors[i].getWriteMethod();
			}
		} catch (IntrospectionException e) {
			logger.error(e.getMessage(), e);
		}
		return methods;
	}
	
	private List<String> toSetterMethodName(String... notFill) {
		List<String> notFillMethodNames = Lists.newArrayList();
		if (notFill != null && notFill.length > 0) {
			for (String fieldName : notFill) {
				notFillMethodNames
					.add("set" + org.apache.commons.lang.StringUtils.capitalize(fieldName));
			}
		}
		return notFillMethodNames;
	}
	
	/**
	 * 根据类型及字段名填充默认值
	 * @param newInstance
	 * @param m
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private <T> void invokeByTypeAndName(T newInstance, Method m) throws IllegalAccessException,
																	InstantiationException {
		String name = m.getName();
		Class<?> paramType = m.getParameterTypes()[0];
		try {
			//String特殊处理
			if (paramType.equals(String.class)) {
				if (name.contains("Id") || name.contains("ID") || name.contains("identityNo")) {
					//身份证
					m.invoke(newInstance, DefaultTestData.SOME_IDENTITY_CARD);
				} else if (name.contains("UserId") || name.contains("UserID")) {
					//用户ID
					m.invoke(newInstance, DefaultTestData.SOME_STRING_20);
				} else if (name.contains("Username") || name.contains("UserName")) {
					//用户名
					m.invoke(newInstance, DefaultTestData.SOME_USER_NAME);
				} else if (name.contains("Password") || name.contains("PassWord")) {
					//用户密码
					m.invoke(newInstance, DefaultTestData.SOME_USER_PASSWD);
				} else if (name.contains("Email") || name.contains("Mail")) {
					//Email
					m.invoke(newInstance, DefaultTestData.SOME_EMAIL);
				} else if (name.contains("Memo") || name.contains("Remark")) {
					//备注
					m.invoke(newInstance, DefaultTestData.SOME_REMARK);
				} else if (name.contains("Mobile") || name.contains("Phone")
							|| name.contains("Telephone")) {
					//手机
					m.invoke(newInstance, DefaultTestData.SOME_MOBILE);
				} else if (name.contains("Status")) {
					//状态
					m.invoke(newInstance, DefaultTestData.SOME_STATUS);
				} else if (name.contains("No") || name.contains("Number")) {
					//业务流水号
					m.invoke(newInstance, BusinessNumberUtil.gainNumber());
				} else {
					m.invoke(newInstance, DefaultTestData.SOME_STRING + "_" + sameTypeFieldCount);
					sameTypeFieldCount++;
				}
				//primitive和wrapper
			} else if (paramType.equals(boolean.class) || paramType.equals(Boolean.class)) {
				m.invoke(newInstance, true);
			} else if (paramType.equals(short.class) || paramType.equals(Short.class)) {
				m.invoke(newInstance, DefaultTestData.SOME_SHORT);
			} else if (paramType.equals(int.class) || paramType.equals(Integer.class)) {
				m.invoke(newInstance, DefaultTestData.SOME_INT);
			} else if (paramType.equals(long.class) || paramType.equals(Long.class)) {
				m.invoke(newInstance, DefaultTestData.SOME_LONG);
			} else if (paramType.equals(double.class) || paramType.equals(Double.class)) {
				m.invoke(newInstance, DefaultTestData.SOME_DOUBLE);
				//常用类型
			} else if (paramType.equals(Money.class)) {
				m.invoke(newInstance, DefaultTestData.SOME_MONEY);
			} else if (paramType.equals(Date.class)) {
				if (StringUtils.equals(name, "RawAddTime")
					|| StringUtils.equals(name, "RawUpdateTime")) {
					m.invoke(newInstance, DefaultTestData.NOW_DATE);
				} else {
					m.invoke(newInstance, DefaultTestData.SOME_DATE);
				}
			} else if (paramType.equals(Timestamp.class)) {
				m.invoke(newInstance, DefaultTestData.SOME_TIMESTAMP);
			} else if (paramType.equals(BigDecimal.class)) {
				m.invoke(newInstance, DefaultTestData.SOME_BIGDECIMAL);
				//枚举
			} else if (paramType.isEnum()) {
				m.invoke(newInstance, paramType.getEnumConstants()[0]);
				//集合 
			} else if (paramType.equals(List.class)) {
				m.invoke(newInstance, Lists.newArrayList());
			} else if (paramType.equals(Map.class)) {
				m.invoke(newInstance, Maps.newHashMap());
			} else if (paramType.equals(Set.class)) {
				m.invoke(newInstance, Sets.newHashSet());
				//复合类
			} else {
				m.invoke(newInstance, fillIt(paramType));
			}
		} catch (IllegalArgumentException | InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	//	public <D> D bind(Class<D> dest, Map<String, String> data) throws InstantiationException,
	//																IllegalAccessException,
	//																NoSuchFieldException {
	//		System.out.println("Class=" + dest);
	//		
	//		D newInstance = dest.newInstance();
	//		for (String k : data.keySet()) {
	//			System.out.println("k=" + k);
	//			
	//			if (k == null) {
	//				throw new RuntimeException("CSV题头怎么会有空字符串？！");
	//			}
	//			
	//			if (k.contains(".")) {
	//				HashMap<String, String> dataCopy = Maps.newHashMap(data);
	//				
	//				String[] compositeField = k.split("\\.", 2);
	//				
	//				String fieldName = compositeField[0];
	//				
	//				Field field = ReflectionUtils.getDeclaredField(dest, fieldName);
	//				
	//				if (ReflectionUtils.getFieldValue(newInstance, fieldName) != null) {
	//					continue;
	//				}
	//				
	//				Class<?> type = field.getType();
	//				
	//				Map<String, String> subFieldsData = Maps.newHashMap();
	//				
	//				//列出所有该字段类型的所有字段，可以拉平字段
	//				for (String k2 : dataCopy.keySet()) {
	//					if (k2.contains(".")) {
	//						String[] compositeField2 = k2.split("\\.", 2);
	//						String firstClassfieldName = compositeField2[0];
	//						if (StringUtils.equals(firstClassfieldName, fieldName)) {
	//							subFieldsData.put(compositeField2[1], dataCopy.get(k2));
	//						}
	//					}
	//				}
	//				//递归绑定所有的值
	//				ReflectionUtils.setParameter(newInstance, fieldName, bind(type, subFieldsData));
	//				
	//			} else {
	//				Field field = ReflectionUtils.getDeclaredField(dest, k);
	//				if (field != null) {
	//					Class<?> type = field.getType();
	//					if (!Primitives.allPrimitiveTypes().contains(type)
	//						&& !ACCEPT_NONPRIMITIVE_CLASSES.contains(type)) {
	//						logger.warn("这个类型{}是复杂类型，不晓得怎么处理", k);
	//						continue;
	//					}
	//					ReflectionUtils.setParameter(newInstance, k, data.get(k));
	//					
	//				} else {
	//					logger.warn("字段{}不存在呀，名字搞错没？大小写，1跟l，0跟o之类的搞混没？", k);
	//				}
	//			}
	//		}
	//		return newInstance;
	//	}
	
}
