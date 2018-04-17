package com.csg.statistics.entity;

/**
 * drt_fin_po_account_record-->DrtFinPoAccountRecord 理财账户信息交易明细表(预购)
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
public class DrtFinPoAccountRecordForQuery{
	
	/** 主键ID */
	private String poAccountRecordId;

	/** 预购帐号ID */
	private String poAccountId;

	/** 交易金额数(以分为单位) */
	private Integer poDealMoney;

	/** 交易时间yyyyMMdd */
	private java.sql.Timestamp dealTime;

	/** 交易时间yyyyMMdd */
	private java.sql.Timestamp dealTimeStart;

	/** 交易时间yyyyMMdd */
	private java.sql.Timestamp dealTimeEnd;

	/** 电融通账号ID */
	private String accountId;

	/** 流向(1转入,2转出,3扣缴,) */
	private String isInOut;

	/** 绑定用电户口ID */
	private String elecUserId;

	/** 银行卡ID */
	private String bankId;

	/** 交易类型名称 */
	private String typeName;

	/** 返回交易结果类型 */
	private String result;

	/** 订单号 */
	private String orderCode;

	/**
	 * 主键ID
	 * @param poAccountRecordId 设置 poAccountRecordId 属性值为参数值 poAccountRecordId
	 */
	public void setPoAccountRecordId(String poAccountRecordId) {
		this.poAccountRecordId = poAccountRecordId == null ? null : poAccountRecordId.trim();
	}
	/**
	 * 主键ID
     * @return 获取poAccountRecordId属性值
     */
	public String getPoAccountRecordId() {
		return this.poAccountRecordId;	
	}
	
	/**
	 * 预购帐号ID
	 * @param poAccountId 设置 poAccountId 属性值为参数值 poAccountId
	 */
	public void setPoAccountId(String poAccountId) {
		this.poAccountId = poAccountId == null ? null : poAccountId.trim();
	}
	/**
	 * 预购帐号ID
     * @return 获取poAccountId属性值
     */
	public String getPoAccountId() {
		return this.poAccountId;	
	}
	
	/**
	 * 交易金额数(以分为单位)
	 * @param poDealMoney 设置 poDealMoney 属性值为参数值 poDealMoney
	 */
	public void setPoDealMoney(Integer poDealMoney) {
		this.poDealMoney = poDealMoney;
	}
	/**
	 * 交易金额数(以分为单位)
     * @return 获取poDealMoney属性值
     */
	public Integer getPoDealMoney() {
		return this.poDealMoney;	
	}
	
	/**
	 * 交易时间yyyyMMdd
	 * @param dealTime 设置 dealTime 属性值为参数值 dealTime
	 */
	public void setDealTime(java.sql.Timestamp dealTime) {
		this.dealTime = dealTime;
	}
	/**
	 * 交易时间yyyyMMdd
     * @return 获取dealTime属性值
     */
	public java.sql.Timestamp getDealTime() {
		return this.dealTime;	
	}
	
	/**
	 * 交易时间yyyyMMdd
	 * @param dealTimeStart 设置 dealTimeStart 属性值为参数值 dealTimeStart
	 */
	public void setDealTimeStart(java.sql.Timestamp dealTimeStart) {
		this.dealTimeStart = dealTimeStart;
	}
	/**
	 * 交易时间yyyyMMdd
     * @return 获取dealTimeStart属性值
     */
	public java.sql.Timestamp getDealTimeStart() {
		return this.dealTimeStart;	
	}
	
	/**
	 * 交易时间yyyyMMdd
	 * @param dealTime 设置 dealTime 属性值为参数值 dealTime
	 */
	public void setDealTimeEnd(java.sql.Timestamp dealTimeEnd) {
		this.dealTimeEnd = dealTimeEnd;
	}
	/**
	 * 交易时间yyyyMMdd
     * @return 获取dealTime属性值
     */
	public java.sql.Timestamp getDealTimeEnd() {
		return this.dealTimeEnd;	
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
	 * 流向(1转入,2转出,3扣缴,)
	 * @param isInOut 设置 isInOut 属性值为参数值 isInOut
	 */
	public void setIsInOut(String isInOut) {
		this.isInOut = isInOut == null ? null : isInOut.trim();
	}
	/**
	 * 流向(1转入,2转出,3扣缴,)
     * @return 获取isInOut属性值
     */
	public String getIsInOut() {
		return this.isInOut;	
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
	 * 银行卡ID
	 * @param bankId 设置 bankId 属性值为参数值 bankId
	 */
	public void setBankId(String bankId) {
		this.bankId = bankId == null ? null : bankId.trim();
	}
	/**
	 * 银行卡ID
     * @return 获取bankId属性值
     */
	public String getBankId() {
		return this.bankId;	
	}
	
	/**
	 * 交易类型名称
	 * @param typeName 设置 typeName 属性值为参数值 typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}
	/**
	 * 交易类型名称
     * @return 获取typeName属性值
     */
	public String getTypeName() {
		return this.typeName;	
	}
	
	/**
	 * 返回交易结果类型
	 * @param result 设置 result 属性值为参数值 result
	 */
	public void setResult(String result) {
		this.result = result == null ? null : result.trim();
	}
	/**
	 * 返回交易结果类型
     * @return 获取result属性值
     */
	public String getResult() {
		return this.result;	
	}
	
	/**
	 * 订单号
	 * @param orderCode 设置 orderCode 属性值为参数值 orderCode
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode == null ? null : orderCode.trim();
	}
	/**
	 * 订单号
     * @return 获取orderCode属性值
     */
	public String getOrderCode() {
		return this.orderCode;	
	}
	
	
}
	

	
