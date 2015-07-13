/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年7月13日 下午6:32:22 创建
 */
package info.maizz.lifebetterkit.designpattern.template.templatemethod;

import info.maizz.lifebetterkit.designpattern.template.Form;
import info.maizz.lifebetterkit.designpattern.template.QueryTemplate;
import info.maizz.lifebetterkit.entities.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author lvchen@yiji.com
 *
 */
public abstract class AbstractQuery<F extends Form, R extends Result> {
	private Logger logger = LoggerFactory.getLogger(QueryTemplate.class);
	
	public R query(F form) {
		logger.info("查询入参：{}", form);
		
		R r = null;
		
		try {
			//流程框架，所有流程以此为准，实现类将不能改变流程
			r = initResult();
			
			form.check();
			
			r = query(r, form);
			
			r.setSuccess();
			
		} catch (IllegalArgumentException e) {
			logger.info("参数错误，{}", e.getMessage());
			r.setFail("INVALID_PARAM", e.getMessage());
		} catch (Exception e) {
			logger.info("内部错误", e);
			r.setFail("INTERNAL_ERROR", "系统内部错误");
		}
		
		logger.info("查询出参：{},{}", r.getCode(), r.getMessage());
		return r;
	}
	
	/**
	 * 查询方法
	 * @param form
	 * @return
	 */
	protected abstract R query(R r, F form);
	
	/**
	 * 初始化结果
	 * @return
	 */
	protected abstract R initResult();
	
}
