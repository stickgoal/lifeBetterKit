/*
maizz.info 
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lucas(stick.goal@163.com) 2015年7月13日 下午5:35:25 创建
 */
package info.maizz.lifebetterkit.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 *
 * @author lucas(stick.goal@163.com)
 *
 */
public abstract class ResultBase implements Result, Serializable {
	
	private static final long serialVersionUID = 2134890551683375544L;
	
	private ResultStatus status;
	
	private String code;
	
	private String message;
	
	/**
	 * @return
	 * @see info.maizz.lifebetterkit.entities.Result#isSuccess()
	 */
	@Override
	public boolean isSuccess() {
		return status == ResultStatus.SUCCESS;
	}
	
	/**
	 * @return
	 * @see info.maizz.lifebetterkit.entities.Result#getCode()
	 */
	@Override
	public String getCode() {
		return code;
	}
	
	/**
	 * @return
	 * @see info.maizz.lifebetterkit.entities.Result#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @see info.maizz.lifebetterkit.entities.Result#setSuccess()
	 */
	@Override
	public void setSuccess() {
		this.status = ResultStatus.SUCCESS;
		this.code = "SUCCESS";
		this.message = "执行成功";
	}
	
	/**
	 * @param code
	 * @param message
	 * @see info.maizz.lifebetterkit.entities.Result#setFail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setFail(String code, String message) {
		this.status = ResultStatus.FAIL;
		this.code = code;
		this.message = message;
	}
	
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
