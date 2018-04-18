package com.csg.intshop.common.enums;

/**
 * 	异常枚举
 *
 * @author  曾令鹏
 * @since   jdk1.8
 * @version 2018年1月23日 曾令鹏
 */
public enum ErrorEnum {

	SOCKET_TIMEOUT("11", "处理超时"),
	CONNECT_TIMEOUT("12", "连接超时"),
	
	SUCCESS("1", "成功"),
	SIGN_FAILURE("2", "认证签名异常"),
	AUTH_FAILURE("3", "账户认证失败（用户或密码不正确）"),
	AUTH_INVALID("4", "账户认证失败（账户已失效或锁定）"),
	SERVER_EXCEPTION("5", "服务器异常"),

	NORMAL("00", "正常"),
	TOKEN_INVALID("01", "token失效"),
	REQUIRED("02", "非空项为空"),
	NETWORK_EXCEPTION("03", "网络异常"),
	OVERLONG_DATA_EXCEPTION("04", "数据超长"),
	RESPONSE_TIMEOUT("05", "响应超时"),
	BUSINESS_EXCEPTION("06", "业务异常"),
	ID_NOT_EXIST_EXCEPTION("07", "ID不存在"),
	
	OTHER_EXCEPTION("99", "系统异常");
	
	
	/** 异常代码KEY */
	private String resultCode;
	
	/** 异常信息 */
	private String resultMessage;
	
	private ErrorEnum(String resultCode, String resultMessage){
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}
	/**
	 * 通过异常代码KEY或企业异常枚举
	 * @param resultCode 异常代码KEY
	 * @return ErrorEnum 异常枚举，若resultCode不存在，返回ErrorEnum.OTHER_EXCEPTION
	 */
	public static ErrorEnum getErrorEnum(String resultCode){
		ErrorEnum[] errorEnums = ErrorEnum.values();
		for (ErrorEnum errorEnum : errorEnums) {
			if(errorEnum.getResultCode().equals(resultCode)){
				return errorEnum;
			}
		}
		return OTHER_EXCEPTION;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}
