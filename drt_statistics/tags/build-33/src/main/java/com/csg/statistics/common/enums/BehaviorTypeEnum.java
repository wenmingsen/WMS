package com.csg.statistics.common.enums;

/**
 * 行为类型枚举
 *
 * @author  曾令鹏
 * @since   jdk1.8
 * @version 2017年12月19日 曾令鹏
 */
public enum BehaviorTypeEnum {

	VISIT(0, "访问"),
	
	REGISTER(1, "注册"),
	
	ELEC_NUMBER(2, "绑定用电户号"),
	
	BANK_CARD(3, "绑定银行卡"),
	
	PO_MONEY(4, "预购电费");
	
	/** 值 */
	private Integer value;
	
	/** 说明 */
	private String text;
	
	private BehaviorTypeEnum(Integer value, String text){
		this.value = value;
		this.text = text;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
