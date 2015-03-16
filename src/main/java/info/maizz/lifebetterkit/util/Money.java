/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年3月16日 上午10:22:53 创建
 */
package info.maizz.lifebetterkit.util;

import java.math.BigDecimal;

/**
 *
 *
 * @author lvchen@yiji.com
 *
 */
public class Money extends BigDecimal {
	
	/**
	 * @param val
	 */
	public Money(double val) {
		super(val);
	}
	
	public Money(char[] val) {
		super(val);
	}
	
	public Money(String val) {
		super(val);
	}
	
	private static final long serialVersionUID = -8734382571213969996L;
	
	/**
	 * @return
	 * @see java.math.BigDecimal#toString()
	 */
	@Override
	public String toString() {
		return super.toPlainString();
	}
	
	/**
	 * @param x
	 * @return
	 * @see java.math.BigDecimal#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object x) {
		return (x instanceof Money) && ((Money) x).longValue() == this.longValue();
	}
}
