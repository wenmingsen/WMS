package com.csg.statistics.common.enums;

/**
 * 电融通账户状态枚举
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
public enum AccountStatusEnum {
	
	NORMAL("0", "正常"),
	FREEZE("1", "冻结"),
	LOCKED("2", "锁定");
	
	/** 值 */
	private String value;
	
	/** 说明 */
	private String text;
	
	private AccountStatusEnum(String value, String text){
		this.value = value;
		this.text =	text;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
