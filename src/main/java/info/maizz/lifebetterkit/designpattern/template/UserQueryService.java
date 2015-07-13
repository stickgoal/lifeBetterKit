/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午5:34:09 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import java.util.Date;

/**
 * 具体服务类，这里以用户查询服务为例，其他类相仿
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class UserQueryService {
	
	/**
	 * 查询用户
	 * @param form
	 * @return
	 */
	public UserQueryResult queryUser(UserQueryForm form) {
		//具体的服务类只负责实现具体查询逻辑，不需要再处理日志等问题
		return new QueryTemplate().<UserQueryForm, UserQueryResult> query(form,
			new QueryCallBack<UserQueryForm, UserQueryResult>() {
				
				@Override
				public UserQueryResult initResult() {
					return new UserQueryResult();
				}
				
				@Override
				public UserQueryResult query(UserQueryResult r, UserQueryForm form) {
					String userId = form.getUserId();
					//脑补：查找数据库,这里随便写点数据
					UserInfo userInfo = new UserInfo();
					userInfo.setUserId(userId);
					userInfo.setRegTime(new Date());
					userInfo.setUsername("haha@github.com");
					r.setUserInfo(userInfo);
					return r;
				}
			});
	}
	
	/**
	 * 查询用户
	 * @param form
	 * @return
	 */
	public UserQueryResult queryUserAlternative(UserQueryForm form) {
		//另一个具体实现
		return new QueryTemplate().<UserQueryForm, UserQueryResult> query(form,
			new QueryCallBack<UserQueryForm, UserQueryResult>() {
				
				@Override
				public UserQueryResult initResult() {
					return new UserQueryResult();
				}
				
				@Override
				public UserQueryResult query(UserQueryResult r, UserQueryForm form) {
					//实现方法...
					return r;
				}
			});
	}
	
}
