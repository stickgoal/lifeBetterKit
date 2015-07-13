/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午4:57:41 创建
 */
package info.maizz.lifebetterkit.entities;

/**
 *
 * 执行结果对象
 * @author lucas(stick.goal@163.com)
 *
 */
public interface Result {
	
	/**
	 * 是否执行成功
	 * @return
	 */
	public boolean isSuccess();
	
	/**
	 * 结果码
	 * @return
	 */
	public String getCode();
	
	/**
	 * 结果消息
	 * @return
	 */
	public String getMessage();
	
	/**
	 * 设置成功
	 */
	public void setSuccess();
	
	/**
	 * 设置失败
	 * @param code
	 * @param message
	 */
	public void setFail(String code, String message);
	
}
