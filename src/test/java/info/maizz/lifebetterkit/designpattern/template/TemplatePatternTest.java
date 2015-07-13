/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午7:26:07 创建
 */
package info.maizz.lifebetterkit.designpattern.template;

import info.maizz.lifebetterkit.TestCaseBase;
import info.maizz.lifebetterkit.designpattern.template.templatemethod.UserQueryMethodService;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class TemplatePatternTest extends TestCaseBase {
	
	public void testMethodQuery() {
		UserQueryForm form = new UserQueryForm();
		form.setUserId("A cool guy!");
		//模板方法
		UserQueryResult methodResult = new UserQueryMethodService().query(form);
		print(methodResult);
		//模板模式
		UserQueryResult templateResult = new UserQueryService().queryUser(form);
		print(templateResult);
	}
}
