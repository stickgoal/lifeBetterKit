/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年3月13日 上午11:29:05 创建
 */
package info.maizz.lifebetterkit.json.pojo;

import info.maizz.lifebetterkit.util.Money;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 *
 * @author lvchen@yiji.com
 *
 */
public class Info {
	private int age;
	
	private Money price;
	
	@JSONField(format = "yyyyMMdd-HHmmss")
	private Date birthday;
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Money getPrice() {
		return this.price;
	}
	
	public void setPrice(Money price) {
		this.price = price;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
