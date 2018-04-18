package com.csg.intshop.entity;

/**
 * drt_shop_address-->DrtShopAddress 积分商城地址表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public class DrtShopAddress{
	
	/** 主键 */
	private String id;

	/** 一级地址 */
	private String addressLevel1;

	/** 二级地址 */
	private String addressLevel2;

	/** 三级地址 */
	private String addressLevel3;

	/** 详细地址 */
	private String addressOther;

	/** 用户ID */
	private String accountId;

	/** 是否默认 */
	private Integer isDefault;

	/** 创建人ID */
	private String creatorId;

	/** 创建人姓名 */
	private String creatorName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 修改人ID */
	private String modifyId;

	/** 修改人姓名 */
	private String modifyName;

	/** 修改时间 */
	private java.sql.Timestamp modifyTime;

	/** 备注 */
	private String remark;

	/** 手机号码 */
	private String phone;

	/** 收货人姓名 */
	private String name;

	/** 邮政编码 */
	private String postcode;

	/**
	 * 主键
	 * @param id 设置 id 属性值为参数值 id
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	/**
	 * 主键
     * @return 获取id属性值
     */
	public String getId() {
		return this.id;	
	}
	
	/**
	 * 一级地址
	 * @param addressLevel1 设置 addressLevel1 属性值为参数值 addressLevel1
	 */
	public void setAddressLevel1(String addressLevel1) {
		this.addressLevel1 = addressLevel1 == null ? null : addressLevel1.trim();
	}
	/**
	 * 一级地址
     * @return 获取addressLevel1属性值
     */
	public String getAddressLevel1() {
		return this.addressLevel1;	
	}
	
	/**
	 * 二级地址
	 * @param addressLevel2 设置 addressLevel2 属性值为参数值 addressLevel2
	 */
	public void setAddressLevel2(String addressLevel2) {
		this.addressLevel2 = addressLevel2 == null ? null : addressLevel2.trim();
	}
	/**
	 * 二级地址
     * @return 获取addressLevel2属性值
     */
	public String getAddressLevel2() {
		return this.addressLevel2;	
	}
	
	/**
	 * 三级地址
	 * @param addressLevel3 设置 addressLevel3 属性值为参数值 addressLevel3
	 */
	public void setAddressLevel3(String addressLevel3) {
		this.addressLevel3 = addressLevel3 == null ? null : addressLevel3.trim();
	}
	/**
	 * 三级地址
     * @return 获取addressLevel3属性值
     */
	public String getAddressLevel3() {
		return this.addressLevel3;	
	}
	
	/**
	 * 详细地址
	 * @param addressOther 设置 addressOther 属性值为参数值 addressOther
	 */
	public void setAddressOther(String addressOther) {
		this.addressOther = addressOther == null ? null : addressOther.trim();
	}
	/**
	 * 详细地址
     * @return 获取addressOther属性值
     */
	public String getAddressOther() {
		return this.addressOther;	
	}
	
	/**
	 * 用户ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 用户ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 是否默认
	 * @param isDefault 设置 isDefault 属性值为参数值 isDefault
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 是否默认
     * @return 获取isDefault属性值
     */
	public Integer getIsDefault() {
		return this.isDefault;	
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
	 * 创建人姓名
	 * @param creatorName 设置 creatorName 属性值为参数值 creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}
	/**
	 * 创建人姓名
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
	 * 修改人ID
	 * @param modifyId 设置 modifyId 属性值为参数值 modifyId
	 */
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId == null ? null : modifyId.trim();
	}
	/**
	 * 修改人ID
     * @return 获取modifyId属性值
     */
	public String getModifyId() {
		return this.modifyId;	
	}
	
	/**
	 * 修改人姓名
	 * @param modifyName 设置 modifyName 属性值为参数值 modifyName
	 */
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName == null ? null : modifyName.trim();
	}
	/**
	 * 修改人姓名
     * @return 获取modifyName属性值
     */
	public String getModifyName() {
		return this.modifyName;	
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
	 * 手机号码
	 * @param phone 设置 phone 属性值为参数值 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}
	/**
	 * 手机号码
     * @return 获取phone属性值
     */
	public String getPhone() {
		return this.phone;	
	}
	
	/**
	 * 收货人姓名
	 * @param name 设置 name 属性值为参数值 name
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	/**
	 * 收货人姓名
     * @return 获取name属性值
     */
	public String getName() {
		return this.name;	
	}
	
	/**
	 * 邮政编码
	 * @param postcode 设置 postcode 属性值为参数值 postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? null : postcode.trim();
	}
	/**
	 * 邮政编码
     * @return 获取postcode属性值
     */
	public String getPostcode() {
		return this.postcode;	
	}
	
	
}
	

	
