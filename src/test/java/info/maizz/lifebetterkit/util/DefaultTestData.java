package info.maizz.lifebetterkit.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 默认的测试数据，构造测试数据时使用该类作为默认数据
 *
 * @author lucas
 *
 */
public abstract class DefaultTestData {
	
	public final static String SOME_STRING_20 = "12345678901234567890";
	
	public final static String SOME_STRING = "1234567890";
	
	public final static int SOME_INT = 123;
	
	public final static long SOME_LONG = 123L;
	
	public final static double SOME_DOUBLE = 123.00;
	
	public final static Money SOME_MONEY = new Money(100.00);
	
	///////
	public final static String SOME_LOCALE = "zh_CN";
	
	public final static String SOME_USER_NAME = "maizi";
	
	public final static String SOME_USER_PASSWD = "maizi123";
	
	public final static String SOME_IP = "127.0.0.1";
	
	public final static String SOME_IDENTITY_CARD = "610113131313131313";
	
	public final static String SOME_EMAIL = "xxx@gmail.com";
	
	public final static String SOME_REMARK = "备注YOYO\\(^o^)/~";
	
	public final static String SOME_MOBILE = "15523836745";
	
	public final static String SOME_STATUS = "INIT";
	
	public final static Date SOME_DATE = getDate();
	
	public final static Timestamp SOME_TIMESTAMP = new Timestamp(SOME_DATE.getTime());

	public static final Date NOW_DATE = new Date();

	public static final Short SOME_SHORT = new Short("1");

	public static final Object SOME_BIGDECIMAL = new BigDecimal(12);

	/**
	 * @return 
	 */
	private static Date getDate() {
		Calendar instance = Calendar.getInstance();
		instance.set(2015, 3, 10);
		return instance.getTime();
		
	}
	
}
