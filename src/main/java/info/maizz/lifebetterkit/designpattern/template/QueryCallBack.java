/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午4:54:50 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import info.maizz.lifebetterkit.entities.Result;

/**
 * 
 * 回调接口，具体实现类实现该接口可以完成变化的部分
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public interface QueryCallBack<F extends Form, R extends Result> {
	
	/**
	 * 初始化结果
	 * @return
	 */
	public R initResult();
	
	/**
	 * 查询方法
	 * @param form
	 * @return
	 */
	public R query(R result, F form);
}
