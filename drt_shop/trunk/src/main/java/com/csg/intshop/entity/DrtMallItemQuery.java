package com.csg.intshop.entity;

/**
 * 积分商城商品池-查询类
 *
 * @author  曾令鹏
 * @since   jdk1.8
 * @version 2018年3月12日 曾令鹏
 */
public class DrtMallItemQuery extends DrtMallItem {
	
	/** 关键字 */
	private String keyword;

	/**
	 * 关键字
     * @return 获取keyword属性值
     */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 关键字
	 * @param keyword 设置 keyword 属性值为参数值 keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
}
