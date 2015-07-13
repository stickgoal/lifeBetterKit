/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年6月8日 下午4:28:48 创建
 */
package info.maizz.lifebetterkit.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author lvchen@yiji.com
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Cell {
	
	int lineNum ();
	
	String name();
	
	
}
