package info.maizz.lifebetterkit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;


public class BusinessNumberUtil {
	
	public static String gainNumber() {
		StringBuilder number = new StringBuilder();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		number.append(simpleDateFormat.format(new Date()));
		int a = (int) (Math.random() * 1000);
		if (a < 10 && a > 0) {
			a = a * 100;
		} else if (a >= 10 && a < 100) {
			a = a * 10;
		}
		number.append(a == 0 ? "000" : a);
		return number.toString();
	}
	
	/**
	 * 创建订单号（固定位数为：8位）
	 * @return
	 */
	public static String createSignTradeNo() {
		StringBuilder number = new StringBuilder();
		long millis = Calendar.getInstance().getTimeInMillis();
		String millisSeconds = String.valueOf(millis);
		String lastSevenSeconds = millisSeconds.substring(millisSeconds.length() - 5);
		
		int a = (int) (Math.random() * 1000);
		if (a < 10 && a > 0) {
			a = a * 100;
		} else if (a >= 10 && a < 100) {
			a = a * 10;
		}
		String flag = a == 0 ? "000" : String.valueOf(a);
		String tradeNo = number.append(lastSevenSeconds).append(flag).toString();

		return tradeNo;
	}

	/**
	 * 
	 * @param digit 指定生成验证码的位数(支持1到18位)
	 * @return
	 */
	public static String generateNumber(int digit){
		if(digit <= 0 || digit >= 19){
			throw new IllegalArgumentException("随机数字的位数必须在1至18之间");
		}
		String s = RandomStringUtils.randomNumeric(digit);
		return s;
	}
}
