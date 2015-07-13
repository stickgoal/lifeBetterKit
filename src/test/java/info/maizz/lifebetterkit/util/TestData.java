/*
 * www.yiji.com Inc.
 * Copyright (c) 2014 All Rights Reserved.
 */

/*
 * 修订记录：
 * lvchen@yiji.com 2015年6月2日 上午10:53:04 创建
 */
package info.maizz.lifebetterkit.util;

import java.util.Date;

/**
 *
 *
 * @author lvchen@yiji.com
 *
 */
public class TestData {
	
	protected static final String userZbjId ="8125011";
	
	protected static final String orderNo = "EJS7HiEI6Dgj";
	
	protected static final String TEST_STRATEGY = "TestDefualt";
	
	protected static final String TEST_CHILD_PRODUCT = "测试";
	
	protected static final String TEST_PRODUCT = "融资";
	
	protected static final String TEST_INDUSTRY = "P2P";
	
	protected static final String TEST_ACCOUNT_PROPERTY = "PROFIT";
	
	protected static final String TEST_SCENE_INLET = "01";
	
	/*---------------------------------------------------------
	 * 
	 * -----------------------基础数据--------------------------
	 * 
	 * --------------------------------------------------------
	 */
	//------------------------会员类----------------------------
	/** sdev环境的数据 */
	protected String[] usersDev = new String[] { "20131022020007492642", "20131022020007492644",
												"20131022020007492646", "20131022020007492648",
												"20131022020007492650", "20131022020007492652",
												"20131022020007492654", "20131022020007492656",
												"20131022020007492658", "20131022020007492660",
												"20131022020007492844" };
	
	protected String operatorIdSNET = "20130725020055257227";
	protected String[] qUsersDev = new String[] { "20140128010007501352" };
	
	//------------------------默认入参----------------------------
	//订单号
	/** 商户订单外部流水号,同理,设置为最小,好像大家都是64位哈 **/
	protected String merchantOrderBizNo = orderNo;
	/** 收费三期制定的产品码 */
	protected String productCode;
	/** 系统名 */
	protected String systemName="commonproducts";
	protected Money tradeAmount;
	protected String tradeType;
	//账户类
	/** 会员号 */
	protected String userId = "xxxxxxxxxxxxxxxx";
	/** 商户会员ID **/
	protected String merchantUserId = "20141118020000059222";
	/** 商户卡号 **/
	protected String merchantCardNo = "20141118020000059222";
	/** 商户资金账号 **/
	protected String merchantAccNo = "20141118020000059222";
	/** 市场账户,联动账户 */
	protected String marketReversionAccNo;
	/** 系统入口编码 **/
	protected String inlet = "01";
	/** 异步回执接口group */
	protected String group = "c.y.m.commonproducts.xxx";
	/** 异步回执接口version */
	protected String version = "1.5";
	//银行类
	/** 对公对私标志：Y对公、N对私、NY对公对私 */
	protected String publicTag = "Y";
	/** 真实姓名 */
	protected String realName = "王耍耍";
	/** 银行省名 */
	protected String provName = "重庆";
	/** 银行市名 */
	protected String cityName = "重庆";
	/** 银行简称 **/
	protected String bankCode = "ICBC";
	/** 银行名称 **/
	protected String bankName = "工商银行";
	/** 银行开户账户号(卡号) */
	protected String bankAccountNo = "622xxxxxxxx333322";
	/** 银行账户开户名(卡户名) */
	protected String bankAccountName = "王耍耍";
	/** 银行卡开户行地址 */
	protected String bankAddress = "重庆市沙坪坝";
	/** 延迟处理时间，以小时为单位;0表示立即报送清算 */
	protected int delay = 0;
	/** 业务请求时间，依据此时间计算延时处理时机 */
	protected Date bizApplyTime = new Date();
	//金额
	/** 提现额 */
	protected Money amount = new Money(60);
	//业务参数
	/** 业务端与渠道约定的用于控制渠道路由的标识符,并无枚举约束 */
	protected String bizCode="TEST";
	/** 与bizCode参数类似,控制着业务端下的子业务 */
	protected String subBizCode="TEST_SUB";

	//文字类
	/** 描述 */
	protected String memo = "一个测试而已";
	
	protected String mobile = "11111111111";
	
}
