package info.maizz.lifebetterkit.entities;

/**
 *
 *
 * @author lucas
 *
 */
public enum ResultStatus implements Messageable {
	
	SUCCESS("SUCCESS", "执行成功"),
	
	PROCESSING("PROCESSING", "处理中"),
	
	FAIL("FAIL", "失败");
	
	/** 枚举值 */
	private final String code;
	
	/** 枚举描述 */
	private final String message;
	
	/**
	 *
	 * @param code 枚举值
	 * @param message 枚举描述
	 */
	private ResultStatus(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return Returns the code.
	 */
	@Override
	public String code() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	@Override
	public String message() {
		return message;
	}
	
	/**
	 * 通过枚举<code>code</code>获得枚举
	 *
	 * @param code
	 * @return ResultStatus
	 */
	public static ResultStatus getByCode(String code) {
		for (ResultStatus _enum : values()) {
			if (_enum.getCode().equals(code)) {
				return _enum;
			}
		}
		return null;
	}
	
	/**
	 * 获取全部枚举
	 * 
	 * @return List<ResultStatus>
	 */
	public static java.util.List<ResultStatus> getAllEnum() {
		java.util.List<ResultStatus> list = new java.util.ArrayList<ResultStatus>(values().length);
		for (ResultStatus _enum : values()) {
			list.add(_enum);
		}
		return list;
	}
	
	/**
	 * 获取全部枚举值
	 * 
	 * @return List<String>
	 */
	public static java.util.List<String> getAllEnumCode() {
		java.util.List<String> list = new java.util.ArrayList<String>(values().length);
		for (ResultStatus _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}
	
	/**
	 * 通过code获取msg
	 * @param code 枚举值
	 * @return
	 */
	public static String getMsgByCode(String code) {
		if (code == null) {
			return null;
		}
		ResultStatus _enum = getByCode(code);
		if (_enum == null) {
			return null;
		}
		return _enum.getMessage();
	}
	
	/**
	 * 获取枚举code
	 * @param _enum
	 * @return
	 */
	public static String getCode(ResultStatus _enum) {
		if (_enum == null) {
			return null;
		}
		return _enum.getCode();
	}
}
