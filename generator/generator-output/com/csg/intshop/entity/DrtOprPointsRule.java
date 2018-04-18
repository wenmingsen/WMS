package com.csg.intshop.entity;

/**
 * drt_opr_points_rule-->DrtOprPointsRule 积分规则
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年02月06日 曾令鹏
 */
public class DrtOprPointsRule{
	
	/** 积分规则ID */
	private String pointsRuleId;

	/** 积分规则 */
	private String pointsRule;

	/** 积分规则类型（1.余额积分 2.交费积分 3.推广积分 4.新手礼 5.推荐有奖 6.吐槽有奖） */
	private String pointsRuleType;

	/** 档次 */
	private String grade;

	/** 额度范围最小值 */
	private Integer moneyScopeMin;

	/** 额度范围最大值 */
	private Integer moneyScopeMax;

	/** 电费银行手续费率 */
	private String bankPoundage;

	/** 分成比列 */
	private String bonusColumn;

	/** 积分基准率 */
	private String pointsDatumRate;

	/** 系数 */
	private String coefficient;

	/** 日收益 */
	private String dayIncome;

	/** 单位获客成本 */
	private String achieveCustomerCost;

	/** 状态（0.停用 1.启用） */
	private String status;

	/** 备注 */
	private String remark;

	/** 创建人ID */
	private String creatorId;

	/** 创建人 */
	private String creatorName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 最后修改人ID */
	private String lastModifierId;

	/** 最后修改人 */
	private String lastModifierName;

	/** 最后修改时间 */
	private java.sql.Timestamp lastModifyTime;

	/** 是否删除,0是，1否 */
	private String isDelete;

	/**
	 * 积分规则ID
	 * @param pointsRuleId 设置 pointsRuleId 属性值为参数值 pointsRuleId
	 */
	public void setPointsRuleId(String pointsRuleId) {
		this.pointsRuleId = pointsRuleId == null ? null : pointsRuleId.trim();
	}
	/**
	 * 积分规则ID
     * @return 获取pointsRuleId属性值
     */
	public String getPointsRuleId() {
		return this.pointsRuleId;	
	}
	
	/**
	 * 积分规则
	 * @param pointsRule 设置 pointsRule 属性值为参数值 pointsRule
	 */
	public void setPointsRule(String pointsRule) {
		this.pointsRule = pointsRule == null ? null : pointsRule.trim();
	}
	/**
	 * 积分规则
     * @return 获取pointsRule属性值
     */
	public String getPointsRule() {
		return this.pointsRule;	
	}
	
	/**
	 * 积分规则类型（1.余额积分 2.交费积分 3.推广积分 4.新手礼 5.推荐有奖 6.吐槽有奖）
	 * @param pointsRuleType 设置 pointsRuleType 属性值为参数值 pointsRuleType
	 */
	public void setPointsRuleType(String pointsRuleType) {
		this.pointsRuleType = pointsRuleType == null ? null : pointsRuleType.trim();
	}
	/**
	 * 积分规则类型（1.余额积分 2.交费积分 3.推广积分 4.新手礼 5.推荐有奖 6.吐槽有奖）
     * @return 获取pointsRuleType属性值
     */
	public String getPointsRuleType() {
		return this.pointsRuleType;	
	}
	
	/**
	 * 档次
	 * @param grade 设置 grade 属性值为参数值 grade
	 */
	public void setGrade(String grade) {
		this.grade = grade == null ? null : grade.trim();
	}
	/**
	 * 档次
     * @return 获取grade属性值
     */
	public String getGrade() {
		return this.grade;	
	}
	
	/**
	 * 额度范围最小值
	 * @param moneyScopeMin 设置 moneyScopeMin 属性值为参数值 moneyScopeMin
	 */
	public void setMoneyScopeMin(Integer moneyScopeMin) {
		this.moneyScopeMin = moneyScopeMin;
	}
	/**
	 * 额度范围最小值
     * @return 获取moneyScopeMin属性值
     */
	public Integer getMoneyScopeMin() {
		return this.moneyScopeMin;	
	}
	
	/**
	 * 额度范围最大值
	 * @param moneyScopeMax 设置 moneyScopeMax 属性值为参数值 moneyScopeMax
	 */
	public void setMoneyScopeMax(Integer moneyScopeMax) {
		this.moneyScopeMax = moneyScopeMax;
	}
	/**
	 * 额度范围最大值
     * @return 获取moneyScopeMax属性值
     */
	public Integer getMoneyScopeMax() {
		return this.moneyScopeMax;	
	}
	
	/**
	 * 电费银行手续费率
	 * @param bankPoundage 设置 bankPoundage 属性值为参数值 bankPoundage
	 */
	public void setBankPoundage(String bankPoundage) {
		this.bankPoundage = bankPoundage == null ? null : bankPoundage.trim();
	}
	/**
	 * 电费银行手续费率
     * @return 获取bankPoundage属性值
     */
	public String getBankPoundage() {
		return this.bankPoundage;	
	}
	
	/**
	 * 分成比列
	 * @param bonusColumn 设置 bonusColumn 属性值为参数值 bonusColumn
	 */
	public void setBonusColumn(String bonusColumn) {
		this.bonusColumn = bonusColumn == null ? null : bonusColumn.trim();
	}
	/**
	 * 分成比列
     * @return 获取bonusColumn属性值
     */
	public String getBonusColumn() {
		return this.bonusColumn;	
	}
	
	/**
	 * 积分基准率
	 * @param pointsDatumRate 设置 pointsDatumRate 属性值为参数值 pointsDatumRate
	 */
	public void setPointsDatumRate(String pointsDatumRate) {
		this.pointsDatumRate = pointsDatumRate == null ? null : pointsDatumRate.trim();
	}
	/**
	 * 积分基准率
     * @return 获取pointsDatumRate属性值
     */
	public String getPointsDatumRate() {
		return this.pointsDatumRate;	
	}
	
	/**
	 * 系数
	 * @param coefficient 设置 coefficient 属性值为参数值 coefficient
	 */
	public void setCoefficient(String coefficient) {
		this.coefficient = coefficient == null ? null : coefficient.trim();
	}
	/**
	 * 系数
     * @return 获取coefficient属性值
     */
	public String getCoefficient() {
		return this.coefficient;	
	}
	
	/**
	 * 日收益
	 * @param dayIncome 设置 dayIncome 属性值为参数值 dayIncome
	 */
	public void setDayIncome(String dayIncome) {
		this.dayIncome = dayIncome == null ? null : dayIncome.trim();
	}
	/**
	 * 日收益
     * @return 获取dayIncome属性值
     */
	public String getDayIncome() {
		return this.dayIncome;	
	}
	
	/**
	 * 单位获客成本
	 * @param achieveCustomerCost 设置 achieveCustomerCost 属性值为参数值 achieveCustomerCost
	 */
	public void setAchieveCustomerCost(String achieveCustomerCost) {
		this.achieveCustomerCost = achieveCustomerCost == null ? null : achieveCustomerCost.trim();
	}
	/**
	 * 单位获客成本
     * @return 获取achieveCustomerCost属性值
     */
	public String getAchieveCustomerCost() {
		return this.achieveCustomerCost;	
	}
	
	/**
	 * 状态（0.停用 1.启用）
	 * @param status 设置 status 属性值为参数值 status
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	/**
	 * 状态（0.停用 1.启用）
     * @return 获取status属性值
     */
	public String getStatus() {
		return this.status;	
	}
	
	/**
	 * 备注
	 * @param remark 设置 remark 属性值为参数值 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
	/**
	 * 备注
     * @return 获取remark属性值
     */
	public String getRemark() {
		return this.remark;	
	}
	
	/**
	 * 创建人ID
	 * @param creatorId 设置 creatorId 属性值为参数值 creatorId
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}
	/**
	 * 创建人ID
     * @return 获取creatorId属性值
     */
	public String getCreatorId() {
		return this.creatorId;	
	}
	
	/**
	 * 创建人
	 * @param creatorName 设置 creatorName 属性值为参数值 creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}
	/**
	 * 创建人
     * @return 获取creatorName属性值
     */
	public String getCreatorName() {
		return this.creatorName;	
	}
	
	/**
	 * 创建时间
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建时间
     * @return 获取createTime属性值
     */
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;	
	}
	
	/**
	 * 最后修改人ID
	 * @param lastModifierId 设置 lastModifierId 属性值为参数值 lastModifierId
	 */
	public void setLastModifierId(String lastModifierId) {
		this.lastModifierId = lastModifierId == null ? null : lastModifierId.trim();
	}
	/**
	 * 最后修改人ID
     * @return 获取lastModifierId属性值
     */
	public String getLastModifierId() {
		return this.lastModifierId;	
	}
	
	/**
	 * 最后修改人
	 * @param lastModifierName 设置 lastModifierName 属性值为参数值 lastModifierName
	 */
	public void setLastModifierName(String lastModifierName) {
		this.lastModifierName = lastModifierName == null ? null : lastModifierName.trim();
	}
	/**
	 * 最后修改人
     * @return 获取lastModifierName属性值
     */
	public String getLastModifierName() {
		return this.lastModifierName;	
	}
	
	/**
	 * 最后修改时间
	 * @param lastModifyTime 设置 lastModifyTime 属性值为参数值 lastModifyTime
	 */
	public void setLastModifyTime(java.sql.Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	/**
	 * 最后修改时间
     * @return 获取lastModifyTime属性值
     */
	public java.sql.Timestamp getLastModifyTime() {
		return this.lastModifyTime;	
	}
	
	/**
	 * 是否删除,0是，1否
	 * @param isDelete 设置 isDelete 属性值为参数值 isDelete
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}
	/**
	 * 是否删除,0是，1否
     * @return 获取isDelete属性值
     */
	public String getIsDelete() {
		return this.isDelete;	
	}
	
	
}
	

	
