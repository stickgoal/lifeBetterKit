/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午5:44:56 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -8657577988951229573L;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 注册时间
	 */
	private Date regTime;
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getRegTime() {
		return this.regTime;
	}
	
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
