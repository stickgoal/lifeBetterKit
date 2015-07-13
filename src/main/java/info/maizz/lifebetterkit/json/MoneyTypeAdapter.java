/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年3月16日 上午10:37:58 创建
 */
package info.maizz.lifebetterkit.json;

import info.maizz.lifebetterkit.util.Money;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Gson的类型适配器实现
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class MoneyTypeAdapter implements JsonSerializer<Money>, JsonDeserializer<Money> {
	
	private static final Logger logger = LoggerFactory.getLogger(MoneyTypeAdapter.class);
	
	/**
	 * @param json Gson封装过的JSON对象
	 * @param typeOfT 类型信息
	 * @param context
	 * @return
	 * @throws JsonParseException
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 * java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Money deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
																								throws JsonParseException {
		logger.debug("json={},type={},context={}", new Object[] { json, typeOfT, context });
		
		if (json.isJsonNull()) {
			return null;
		}
		String value = json.getAsString();
		return new Money(value);
	}
	
	/**
	 * @param src
	 * @param typeOfSrc
	 * @param context
	 * @return
	 * @see com.google.gson.JsonSerializer#serialize(java.lang.Object,
	 * java.lang.reflect.Type, com.google.gson.JsonSerializationContext)
	 */
	@Override
	public JsonElement serialize(Money src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.toString());
	}
	
}
