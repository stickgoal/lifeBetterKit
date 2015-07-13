/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年7月13日 下午4:44:19 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * 表单对象，用于封装表单参数
 * 
 * @author lvchen@yiji.com
 *
 */
public abstract class Form implements Serializable {
	
	private static final long serialVersionUID = 5671088527198269176L;
	
	/**
	 * 参数校验方法
	 */
	public abstract void check();
	
	/**
	 * 子类不需要再写toString
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
