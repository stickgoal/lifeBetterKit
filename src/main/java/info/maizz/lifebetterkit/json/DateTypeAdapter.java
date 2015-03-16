/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年3月16日 下午2:02:50 创建
 */
package info.maizz.lifebetterkit.json;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 *
 *
 * @author lvchen@yiji.com
 *
 */
public class DateTypeAdapter implements JsonDeserializer<Date> {
	
	public static final String HRB_DATE_FORMAT = " yyyyMMdd-HHmmss";
	
	/**
	 * @param json
	 * @param typeOfT
	 * @param context
	 * @return
	 * @throws JsonParseException
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement,
	 * java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
																								throws JsonParseException {
		if (json.isJsonNull()) {
			return null;
		}
		
		return null;
	}
}
