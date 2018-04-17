package com.csg.statistics.common.enums;


/**
 * 数值类型枚举
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
public enum NumberTypeEnum {

	/** 正数 */
	PLUS("1", "正数"),
	
	/** 负数 */
	MINUS("0", "负数");
	
	/** 值 */
	private String value;
	
	/** 说明 */
	private String text;
	
	
	private NumberTypeEnum(String value, String text){
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

