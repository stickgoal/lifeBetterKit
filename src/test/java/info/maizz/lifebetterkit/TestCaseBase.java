/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年3月16日 上午10:31:03 创建
 */
package info.maizz.lifebetterkit;

import junit.framework.TestCase;

import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class TestCaseBase extends TestCase {
	
	protected void print(Object obj) {
		System.out.println(StringUtils.repeat("=", 20));
		System.out.println("\t" + obj.toString());
		System.out.println(StringUtils.repeat("=", 20));
	}
	
}
