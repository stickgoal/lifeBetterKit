/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年3月13日 上午11:28:12 创建
 */
package info.maizz.lifebetterkit.json.pojo;

import info.maizz.lifebetterkit.util.Money;

import java.beans.IntrospectionException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class Dog {
	
	private List<Info> infos;
	
	private Date brithday;
	
	public Date getBrithday() {
		return this.brithday;
	}
	
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	
	public List<Info> getInfos() {
		return this.infos;
	}
	
	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}
	
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public static void main(String[] args) throws IntrospectionException, NoSuchFieldException,
											SecurityException {
		
		//		JSON.DEFFAULT_DATE_FORMAT = "yyyyMMdd-HHmmss";
		//		Dog parse = JSON.parseObject("{\"brithday\":\"20141101 190112\"}", Dog.class);
		
		Dog d = new Dog();
		d.setBrithday(new Date());
		List<Info> i = Lists.newArrayList();
		Info e = new Info();
		e.setAge(1);
		e.setBirthday(new Date());
		e.setPrice(new Money(100.00));
		i.add(e);
		d.setInfos(i);
		String jsonString = JSON.toJSONString(d);
		System.out.println(jsonString);
		//		System.out.println(parse.getInfos().get(0).getBirthday());
	}
	
}
