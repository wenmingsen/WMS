package com.csg.intshop.entity;

/**
 * drt_administrative_region-->DrtAdministrativeRegion 
 *
 * @author  刘磊
 * @since   1.8
 * @version 2017年12月28日 刘磊
 */
public class DrtAdministrativeRegion{
	
	/** 行政区域编码 */
	private String administrativeRegionId;

	/** 行政区域编号 */
	private String administrativeRegionCode;

	/** 行政区域名称 */
	private String administrativeRegionName;

	/** 行政区域简码 */
	private String administrativeRegionBriefCode;

	/** 行政级别 */
	private String administrativeGrade;

	/** 父区域编码 */
	private String parentAdministrativeRegionId;

	/** 排序 */
	private Integer sortNum;

	/** 状态（0=无效，1=有效，2=冻结） */
	private String status;

	/** 创建人ID */
	private String createrId;

	/** 创建人名称 */
	private String createrName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 修改人ID */
	private String modifierId;

	/** 修改人名称 */
	private String modifierName;

	/** 修改时间 */
	private java.sql.Timestamp modifyTime;

	/**
	 * 行政区域编码
	 * @param administrativeRegionId 设置 administrativeRegionId 属性值为参数值 administrativeRegionId
	 */
	public void setAdministrativeRegionId(String administrativeRegionId) {
		this.administrativeRegionId = administrativeRegionId == null ? null : administrativeRegionId.trim();
	}
	/**
	 * 行政区域编码
     * @return 获取administrativeRegionId属性值
     */
	public String getAdministrativeRegionId() {
		return this.administrativeRegionId;	
	}
	
	/**
	 * 行政区域编号
	 * @param administrativeRegionCode 设置 administrativeRegionCode 属性值为参数值 administrativeRegionCode
	 */
	public void setAdministrativeRegionCode(String administrativeRegionCode) {
		this.administrativeRegionCode = administrativeRegionCode == null ? null : administrativeRegionCode.trim();
	}
	/**
	 * 行政区域编号
     * @return 获取administrativeRegionCode属性值
     */
	public String getAdministrativeRegionCode() {
		return this.administrativeRegionCode;	
	}
	
	/**
	 * 行政区域名称
	 * @param administrativeRegionName 设置 administrativeRegionName 属性值为参数值 administrativeRegionName
	 */
	public void setAdministrativeRegionName(String administrativeRegionName) {
		this.administrativeRegionName = administrativeRegionName == null ? null : administrativeRegionName.trim();
	}
	/**
	 * 行政区域名称
     * @return 获取administrativeRegionName属性值
     */
	public String getAdministrativeRegionName() {
		return this.administrativeRegionName;	
	}
	
	/**
	 * 行政区域简码
	 * @param administrativeRegionBriefCode 设置 administrativeRegionBriefCode 属性值为参数值 administrativeRegionBriefCode
	 */
	public void setAdministrativeRegionBriefCode(String administrativeRegionBriefCode) {
		this.administrativeRegionBriefCode = administrativeRegionBriefCode == null ? null : administrativeRegionBriefCode.trim();
	}
	/**
	 * 行政区域简码
     * @return 获取administrativeRegionBriefCode属性值
     */
	public String getAdministrativeRegionBriefCode() {
		return this.administrativeRegionBriefCode;	
	}
	
	/**
	 * 行政级别
	 * @param administrativeGrade 设置 administrativeGrade 属性值为参数值 administrativeGrade
	 */
	public void setAdministrativeGrade(String administrativeGrade) {
		this.administrativeGrade = administrativeGrade == null ? null : administrativeGrade.trim();
	}
	/**
	 * 行政级别
     * @return 获取administrativeGrade属性值
     */
	public String getAdministrativeGrade() {
		return this.administrativeGrade;	
	}
	
	/**
	 * 父区域编码
	 * @param parentAdministrativeRegionId 设置 parentAdministrativeRegionId 属性值为参数值 parentAdministrativeRegionId
	 */
	public void setParentAdministrativeRegionId(String parentAdministrativeRegionId) {
		this.parentAdministrativeRegionId = parentAdministrativeRegionId == null ? null : parentAdministrativeRegionId.trim();
	}
	/**
	 * 父区域编码
     * @return 获取parentAdministrativeRegionId属性值
     */
	public String getParentAdministrativeRegionId() {
		return this.parentAdministrativeRegionId;	
	}
	
	/**
	 * 排序
	 * @param sortNum 设置 sortNum 属性值为参数值 sortNum
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	/**
	 * 排序
     * @return 获取sortNum属性值
     */
	public Integer getSortNum() {
		return this.sortNum;	
	}
	
	/**
	 * 状态（0=无效，1=有效，2=冻结）
	 * @param status 设置 status 属性值为参数值 status
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	/**
	 * 状态（0=无效，1=有效，2=冻结）
     * @return 获取status属性值
     */
	public String getStatus() {
		return this.status;	
	}
	
	/**
	 * 创建人ID
	 * @param createrId 设置 createrId 属性值为参数值 createrId
	 */
	public void setCreaterId(String createrId) {
		this.createrId = createrId == null ? null : createrId.trim();
	}
	/**
	 * 创建人ID
     * @return 获取createrId属性值
     */
	public String getCreaterId() {
		return this.createrId;	
	}
	
	/**
	 * 创建人名称
	 * @param createrName 设置 createrName 属性值为参数值 createrName
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName == null ? null : createrName.trim();
	}
	/**
	 * 创建人名称
     * @return 获取createrName属性值
     */
	public String getCreaterName() {
		return this.createrName;	
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
	 * 修改人ID
	 * @param modifierId 设置 modifierId 属性值为参数值 modifierId
	 */
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId == null ? null : modifierId.trim();
	}
	/**
	 * 修改人ID
     * @return 获取modifierId属性值
     */
	public String getModifierId() {
		return this.modifierId;	
	}
	
	/**
	 * 修改人名称
	 * @param modifierName 设置 modifierName 属性值为参数值 modifierName
	 */
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName == null ? null : modifierName.trim();
	}
	/**
	 * 修改人名称
     * @return 获取modifierName属性值
     */
	public String getModifierName() {
		return this.modifierName;	
	}
	
	/**
	 * 修改时间
	 * @param modifyTime 设置 modifyTime 属性值为参数值 modifyTime
	 */
	public void setModifyTime(java.sql.Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 修改时间
     * @return 获取modifyTime属性值
     */
	public java.sql.Timestamp getModifyTime() {
		return this.modifyTime;	
	}
	
	
}
	
