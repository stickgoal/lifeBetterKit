/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午4:35:20 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import info.maizz.lifebetterkit.entities.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * yes,传说中的查询模板
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class QueryTemplate {
	
	private Logger logger = LoggerFactory.getLogger(QueryTemplate.class);
	
	/**
	 * 查询方法，抽取的是所有查询方法共用的部分，扩展的部分由回调方法提供
	 * @param form 查询表单
	 * @param callBack 查询回调，由实际实现类提供
	 * @return
	 */
	public <F extends Form, R extends Result> R query(F form, QueryCallBack<F, R> callBack) {
		logger.info("查询入参：{}", form);
		
		R r = null;
		
		try {
			//流程框架，所有流程以此为准，实现类将不能改变流程
			r = callBack.initResult();
			
			form.check();
			
			r = callBack.query(r, form);
			
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
}
