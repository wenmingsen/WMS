package com.csg.statistics.common.enums;

/**
 * 电子商城接口地址 枚举
 *
 * @author  曾令鹏
 * @since   jdk1.8
 * @version 2018年1月22日 曾令鹏
 */
public enum ElectronicMallApiUrlEnum {

	GET_TOKEN("/getToken", "积分商城获取电子商城token"),
	REFRESH_TOKEN("/refresh_Token", " 积分商城获取电子商城token"),
	CONFIRM_ORDER("/confirmOrder", "积分商城调用电子商城下单接口"),
	GET_SKU_INFO_LIST("/getSkuInfoList", " 获取商品信息接口"),
	GET_SKU_CODE("/getSkuCode", "获取商品池商品编号接口"),
	GET_SKU_DETAIL_INFO("/getSkuDetailInfo", "获取商品详情接口"),
	GET_ORDER_WAYBILL_DETAIL("/getOrderWaybillDetail", "积分商城调用电子商城物流信息接口"),
	SYN_RECEIPT_CONFRIM("/synReceiptConfrim", "积分商城确认收货调用南网商城接口");
	

	/** 值 */
	private String value;
	
	/** 说明 */
	private String text;
	
	private ElectronicMallApiUrlEnum(String value, String text){
		this.value = value;
		this.text = text;
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
