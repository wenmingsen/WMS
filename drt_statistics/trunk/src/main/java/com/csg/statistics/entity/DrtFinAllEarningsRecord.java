package com.csg.statistics.entity;

/**
 * drt_fin_all_earnings_record-->DrtFinAllEarningsRecord 积分收支明细表(当前表)
 *
 * @author  徐辰
 * @since   1.8
 * @version 2018年02月28日 徐辰
 */
public class DrtFinAllEarningsRecord{
	
	/** 主键ID */
	private String poEarningsRecordId;

	/** 预购帐号id */
	private String poAccountId;

	/** 电融通账号ID */
	private String accountId;

	/** 绑定用电户口ID */
	private String elecUserId;

	/** 本次积分变更数 */
	private Integer earnings;

	/** 当前预购账户收益总额(当来源类型为0和1时有数据) */
	private Integer totalEarnings;

	/** 本次记录时间 */
	private java.sql.Timestamp recordTime;

	/** 正负值（0为负，1为正） */
	private String pmType;

	/** 来源类型(0预购电费收益,1交电费收益,2活动奖励收益,9积分兑换,10积分异常处理) */
	private String fromType;

	/** 来源类型名称 */
	private String fromName;

	/** 订单号(商城积分兑换才会有) */
	private String orderNo;

	/** 0-待上报 1-审批中 2-审批通过 3-不通过 */
	private Integer auditState;

	/** 当前电融通积分余额 */
	private Integer currentEarnings;

	/** 最后修改时间：yyyyMMddHHmmss */
	private Long lastModifyTime;

	/** 实际收益日：yyyyMMdd */
	private Integer currDay;

	/** 积分到期日期：yyyyMMdd */
	private Integer expiryDay;

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
	 * 本次积分变更数
	 * @param earnings 设置 earnings 属性值为参数值 earnings
	 */
	public void setEarnings(Integer earnings) {
		this.earnings = earnings;
	}
	/**
	 * 本次积分变更数
     * @return 获取earnings属性值
     */
	public Integer getEarnings() {
		return this.earnings;	
	}
	
	/**
	 * 当前预购账户收益总额(当来源类型为0和1时有数据)
	 * @param totalEarnings 设置 totalEarnings 属性值为参数值 totalEarnings
	 */
	public void setTotalEarnings(Integer totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	/**
	 * 当前预购账户收益总额(当来源类型为0和1时有数据)
     * @return 获取totalEarnings属性值
     */
	public Integer getTotalEarnings() {
		return this.totalEarnings;	
	}
	
	/**
	 * 本次记录时间
	 * @param recordTime 设置 recordTime 属性值为参数值 recordTime
	 */
	public void setRecordTime(java.sql.Timestamp recordTime) {
		this.recordTime = recordTime;
	}
	/**
	 * 本次记录时间
     * @return 获取recordTime属性值
     */
	public java.sql.Timestamp getRecordTime() {
		return this.recordTime;	
	}
	
	/**
	 * 正负值（0为负，1为正）
	 * @param pmType 设置 pmType 属性值为参数值 pmType
	 */
	public void setPmType(String pmType) {
		this.pmType = pmType == null ? null : pmType.trim();
	}
	/**
	 * 正负值（0为负，1为正）
     * @return 获取pmType属性值
     */
	public String getPmType() {
		return this.pmType;	
	}
	
	/**
	 * 来源类型(0预购电费收益,1交电费收益,2活动奖励收益,9积分兑换,10积分异常处理)
	 * @param fromType 设置 fromType 属性值为参数值 fromType
	 */
	public void setFromType(String fromType) {
		this.fromType = fromType == null ? null : fromType.trim();
	}
	/**
	 * 来源类型(0预购电费收益,1交电费收益,2活动奖励收益,9积分兑换,10积分异常处理)
     * @return 获取fromType属性值
     */
	public String getFromType() {
		return this.fromType;	
	}
	
	/**
	 * 来源类型名称
	 * @param fromName 设置 fromName 属性值为参数值 fromName
	 */
	public void setFromName(String fromName) {
		this.fromName = fromName == null ? null : fromName.trim();
	}
	/**
	 * 来源类型名称
     * @return 获取fromName属性值
     */
	public String getFromName() {
		return this.fromName;	
	}
	
	/**
	 * 订单号(商城积分兑换才会有)
	 * @param orderNo 设置 orderNo 属性值为参数值 orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}
	/**
	 * 订单号(商城积分兑换才会有)
     * @return 获取orderNo属性值
     */
	public String getOrderNo() {
		return this.orderNo;	
	}
	
	/**
	 * 0-待上报 1-审批中 2-审批通过 3-不通过
	 * @param auditState 设置 auditState 属性值为参数值 auditState
	 */
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	/**
	 * 0-待上报 1-审批中 2-审批通过 3-不通过
     * @return 获取auditState属性值
     */
	public Integer getAuditState() {
		return this.auditState;	
	}
	
	/**
	 * 当前电融通积分余额
	 * @param currentEarnings 设置 currentEarnings 属性值为参数值 currentEarnings
	 */
	public void setCurrentEarnings(Integer currentEarnings) {
		this.currentEarnings = currentEarnings;
	}
	/**
	 * 当前电融通积分余额
     * @return 获取currentEarnings属性值
     */
	public Integer getCurrentEarnings() {
		return this.currentEarnings;	
	}
	
	/**
	 * 最后修改时间：yyyyMMddHHmmss
	 * @param lastModifyTime 设置 lastModifyTime 属性值为参数值 lastModifyTime
	 */
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	/**
	 * 最后修改时间：yyyyMMddHHmmss
     * @return 获取lastModifyTime属性值
     */
	public Long getLastModifyTime() {
		return this.lastModifyTime;	
	}
	
	/**
	 * 实际收益日：yyyyMMdd
	 * @param currDay 设置 currDay 属性值为参数值 currDay
	 */
	public void setCurrDay(Integer currDay) {
		this.currDay = currDay;
	}
	/**
	 * 实际收益日：yyyyMMdd
     * @return 获取currDay属性值
     */
	public Integer getCurrDay() {
		return this.currDay;	
	}
	
	/**
	 * 积分到期日期：yyyyMMdd
	 * @param expiryDay 设置 expiryDay 属性值为参数值 expiryDay
	 */
	public void setExpiryDay(Integer expiryDay) {
		this.expiryDay = expiryDay;
	}
	/**
	 * 积分到期日期：yyyyMMdd
     * @return 获取expiryDay属性值
     */
	public Integer getExpiryDay() {
		return this.expiryDay;	
	}
	
	
}
	

	
