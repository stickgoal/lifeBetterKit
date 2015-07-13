/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年7月13日 下午5:34:43 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import info.maizz.lifebetterkit.entities.ResultBase;

/**
 *
 *
 * @author lvchen@yiji.com
 *
 */
public class UserQueryResult extends ResultBase {
	
	private static final long serialVersionUID = 2165276126971658543L;
	
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
}
