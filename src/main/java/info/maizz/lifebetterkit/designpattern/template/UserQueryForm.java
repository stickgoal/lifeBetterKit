/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午4:45:30 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * 只做演示用，实际场景建议使用jsr303处理 具体验证方式请参考 <a href=
 * "http://docs.jboss.org/hibernate/validator/4.2/reference/zh-CN/html/validator-usingvalidator.html"
 * >hibernate validator中文文档</a>
 * @author lucas(stick.goal@163.com)
 *
 */
public class UserQueryForm extends Form {
	
	private static final long serialVersionUID = -3477296194022587898L;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 注册开始时间
	 */
	private Date regStartTime;
	
	/**
	 * 注册结束时间
	 */
	private Date regEndTime;
	
	/**
	 * 
	 * @see info.maizz.lifebetterkit.designpattern.template.Form#check()
	 */
	@Override
	public void check() {
		if (StringUtils.isBlank(userId)) {
			throw new IllegalArgumentException("userId 不能为空");
		}
		
		if (regStartTime != null && regEndTime != null && regStartTime.after(regEndTime)) {
			throw new IllegalArgumentException("注册时间区间不合法，起始时间应当在结束时间之后");
		}
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Date getRegStartTime() {
		return this.regStartTime;
	}
	
	public void setRegStartTime(Date regStartTime) {
		this.regStartTime = regStartTime;
	}
	
	public Date getRegEndTime() {
		return this.regEndTime;
	}
	
	public void setRegEndTime(Date regEndTime) {
		this.regEndTime = regEndTime;
	}
	
}
