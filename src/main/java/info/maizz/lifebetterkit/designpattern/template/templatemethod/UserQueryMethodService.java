/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午6:43:07 创建
 */
package info.maizz.lifebetterkit.designpattern.template.templatemethod;

import info.maizz.lifebetterkit.designpattern.template.UserInfo;
import info.maizz.lifebetterkit.designpattern.template.UserQueryForm;
import info.maizz.lifebetterkit.designpattern.template.UserQueryResult;

import java.util.Date;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class UserQueryMethodService extends AbstractQuery<UserQueryForm, UserQueryResult> {
	
	/**
	 * @param r
	 * @param form
	 * @return
	 * @see info.maizz.lifebetterkit.designpattern.template.templatemethod.AbstractQuery#query(info.maizz.lifebetterkit.entities.Result,
	 * info.maizz.lifebetterkit.designpattern.template.Form)
	 */
	@Override
	protected UserQueryResult query(UserQueryResult r, UserQueryForm form) {
		String userId = form.getUserId();
		//脑补：查找数据库,这里随便写点数据
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userId);
		userInfo.setRegTime(new Date());
		userInfo.setUsername("haha@github.com");
		r.setUserInfo(userInfo);
		return r;
	}
	
	/**
	 * @return
	 * @see info.maizz.lifebetterkit.designpattern.template.templatemethod.AbstractQuery#initResult()
	 */
	@Override
	protected UserQueryResult initResult() {
		return new UserQueryResult();
	}
	
}
