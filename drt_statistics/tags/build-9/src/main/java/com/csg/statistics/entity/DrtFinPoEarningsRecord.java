package com.csg.statistics.entity;

/**
 * drt_fin_po_earnings_record-->DrtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月08日 曾令鹏
 */
public class DrtFinPoEarningsRecord{
	
	/** 主键ID */
	private String poEarningsRecordId;

	/** 预购帐号id */
	private String poAccountId;

	/** 本次积分收益数 */
	private Integer earnings;

	/** 本次记录收益时间 */
	private java.sql.Timestamp time;

	/** 正负值（0为负，1为正） */
	private String type;

	/** 电融通账号ID */
	private String accountId;

	/** 绑定用电户口ID */
	private String elecUserId;

	/** 来源类型 */
	private String fromType;

	/**
	 * 主键ID
	 * @param poEarningsRecordId 设置 poEarningsRecordId 属性值为参数值 poEarningsRecordId
	 */
	public void setPoEarningsRecordId(String poEarningsRecordId) {
		this.poEarningsRecordId = poEarningsRecordId == null ? null : poEarningsRecordId.trim();
	}
	/**
	 * 主键ID
     * @return 获取poEarningsRecordId属性值
     */
	public String getPoEarningsRecordId() {
		return this.poEarningsRecordId;	
	}
	
	/**
	 * 预购帐号id
	 * @param poAccountId 设置 poAccountId 属性值为参数值 poAccountId
	 */
	public void setPoAccountId(String poAccountId) {
		this.poAccountId = poAccountId == null ? null : poAccountId.trim();
	}
	/**
	 * 预购帐号id
     * @return 获取poAccountId属性值
     */
	public String getPoAccountId() {
		return this.poAccountId;	
	}
	
	/**
	 * 本次积分收益数
	 * @param earnings 设置 earnings 属性值为参数值 earnings
	 */
	public void setEarnings(Integer earnings) {
		this.earnings = earnings;
	}
	/**
	 * 本次积分收益数
     * @return 获取earnings属性值
     */
	public Integer getEarnings() {
		return this.earnings;	
	}
	
	/**
	 * 本次记录收益时间
	 * @param time 设置 time 属性值为参数值 time
	 */
	public void setTime(java.sql.Timestamp time) {
		this.time = time;
	}
	/**
	 * 本次记录收益时间
     * @return 获取time属性值
     */
	public java.sql.Timestamp getTime() {
		return this.time;	
	}
	
	/**
	 * 正负值（0为负，1为正）
	 * @param type 设置 type 属性值为参数值 type
	 */
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
	/**
	 * 正负值（0为负，1为正）
     * @return 获取type属性值
     */
	public String getType() {
		return this.type;	
	}
	
	/**
	 * 电融通账号ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 电融通账号ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 绑定用电户口ID
	 * @param elecUserId 设置 elecUserId 属性值为参数值 elecUserId
	 */
	public void setElecUserId(String elecUserId) {
		this.elecUserId = elecUserId == null ? null : elecUserId.trim();
	}
	/**
	 * 绑定用电户口ID
     * @return 获取elecUserId属性值
     */
	public String getElecUserId() {
		return this.elecUserId;	
	}
	
	/**
	 * 来源类型
	 * @param fromType 设置 fromType 属性值为参数值 fromType
	 */
	public void setFromType(String fromType) {
		this.fromType = fromType == null ? null : fromType.trim();
	}
	/**
	 * 来源类型
     * @return 获取fromType属性值
     */
	public String getFromType() {
		return this.fromType;	
	}
	
	
}
	

	
