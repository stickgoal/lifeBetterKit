/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年3月16日 上午10:15:36 创建
 */
package info.maizz.lifebetterkit.json;

import info.maizz.lifebetterkit.TestCaseBase;
import info.maizz.lifebetterkit.json.pojo.Dog;
import info.maizz.lifebetterkit.json.pojo.Info;
import info.maizz.lifebetterkit.util.Money;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class TestGson extends TestCaseBase {
	
	private static final String TEST_DATE_FORMAT = "yyyyMMdd-HHmmss";
	private static final Logger logger = LoggerFactory.getLogger(TestGson.class);
	
	/** 序列化功能 */
	public void testSerialize() {
		Gson gson = new Gson();
		Dog d = newDoggie();
		String json = gson.toJson(d);
		print(json);
		assertNotNull(json);
		
	}
	
	public void testSerializeWithDateFormat() {
		GsonBuilder gson = new GsonBuilder();
		Dog d = newDoggie();
		gson.setDateFormat(TEST_DATE_FORMAT);
		String json = gson.create().toJson(d);
		print(json);
		assertNotNull(json);
		
	}
	
	public void testSerializeWithTypeAdapter() {
		GsonBuilder gson = new GsonBuilder();
		Dog d = newDoggie();
		gson.setDateFormat(TEST_DATE_FORMAT);
		gson.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
		gson.registerTypeAdapter(Money.class, new MoneyTypeAdapter());
		
		String json = gson.create().toJson(d);
		print(json);
		assertNotNull(json);
	}
	
	/** 反序列化 */
	
	private static String jsonDog = "{\"infos\":[{\"age\":\"1\",\"price\":\"103.02\",\"birthday\":\"20150356-104856\"}],\"brithday\":null}";
	
	public void testDeserialize() throws ParseException {
		
		GsonBuilder gson = new GsonBuilder();
		gson.setDateFormat(TEST_DATE_FORMAT);
		gson.registerTypeAdapter(Money.class, new MoneyTypeAdapter());
		Dog trueDog = gson.create().fromJson(jsonDog, Dog.class);
		print(trueDog);
		
		assertNotNull(trueDog);
		assertTrue(trueDog.getInfos() != null && !trueDog.getInfos().isEmpty());
		assertTrue(trueDog.getInfos().size() == 1);
		Info info = trueDog.getInfos().get(0);
		assertEquals(1, info.getAge());
		Date parseDate = DateUtils.parseDate("20150356-10:48:56",
			new String[] { "yyyyMMdd-HH:mm:ss" });
		assertEquals("日期不相等", parseDate, info.getBirthday());
		assertEquals("金额不相等", new Money(103.02), info.getPrice());
		
		assertNull(trueDog.getBrithday());
	}
	
	private static String jsonDogEmptyArray = "{\"infos\":[],\"brithday\":null}";
	
	public void testDeserializeEmptyArray() throws ParseException {
		
		GsonBuilder gson = new GsonBuilder();
		Dog trueDog = gson.create().fromJson(jsonDogEmptyArray, Dog.class);
		print(trueDog);
		
		assertNotNull(trueDog);
		List<Info> infos = trueDog.getInfos();
		assertNotNull(infos);
		assertEquals(0, infos.size());
		assertNull(trueDog.getBrithday());
	}
	
	private static String jsonDogEmptyString = "{\"infos\":\"\",\"brithday\":null}";
	
	public void testDeserializeEmptyString() {
		GsonBuilder gson = new GsonBuilder();
		gson.serializeNulls();
		
		Dog trueDog = gson.create().fromJson(jsonDogEmptyString, Dog.class);
		print(trueDog);
	}
	
	/** 辅助方法 */
	private Dog newDoggie() {
		Dog d = new Dog();
		d.setBrithday(new Date());
		List<Info> i = Lists.newArrayList();
		Info e = new Info();
		e.setAge(1);
		e.setBirthday(new Date());
		e.setPrice(new Money(103.02));
		i.add(e);
		d.setInfos(i);
		return d;
	}
}
