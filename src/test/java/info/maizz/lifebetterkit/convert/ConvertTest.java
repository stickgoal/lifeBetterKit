/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年6月5日 下午2:57:33 创建
 */
package info.maizz.lifebetterkit.convert;

import info.maizz.lifebetterkit.TestCaseBase;

import java.math.BigDecimal;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public class ConvertTest extends TestCaseBase{
	
	private ConversionService conversion = new DefaultConversionService();
	
	public void testStringToNumber() {
		Integer convert = conversion.convert("100", Integer.class);
		assertTrue(convert==100);
	}
	
	public void testStringToBigDeciaml() {
		BigDecimal convert = conversion.convert("100.00", BigDecimal.class);
		assertTrue(convert.compareTo(new BigDecimal(100.00))==0);
	}
	
	public void testStringToBoolean() {
		Boolean convert = conversion.convert("True", Boolean.class);
		assertTrue(convert);
		Boolean convert1 = conversion.convert("on", Boolean.class);
		assertTrue(convert1);
	}
	
}
