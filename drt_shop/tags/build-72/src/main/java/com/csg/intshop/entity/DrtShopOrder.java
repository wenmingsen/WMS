package com.csg.intshop.entity;

import java.util.List;

/**
 * drt_shop_order-->DrtShopOrder 积分商城订单表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
public class DrtShopOrder{
	
	/** 主键 */
	private String id;

	/** 用户ID */
	private String accountId;

	/** 订单积分 */
	private Integer orderEarnings;

	/** 订单号 */
	private String orderNo;

	/** 订单状态 */
	private Integer orderState;

	/** 创建者ID */
	private String creatorId;

	/** 创建人名称 */
	private String creatorName;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 修改者ID */
	private String modifyId;

	/** 修改者名称 */
	private String modifyName;

	/** 上次修改时间 */
	private java.sql.Timestamp modifyTime;

	/** 地址 */
	private String address;

	/** 用户姓名 */
	private String accountName;

	/** 备注 */
	private String remark;

	/**子订单列表*/
	private List<DrtShopOrderDetail> orderSons;
	
	/**收货人*/
	private String name;
	
	/***手机号*/
	private String phone;
	
	/**地址id*/
	private String addressId;
	
	
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
	 * 订单积分
	 * @param orderEarnings 设置 orderEarnings 属性值为参数值 orderEarnings
	 */
	public void setOrderEarnings(Integer orderEarnings) {
		this.orderEarnings = orderEarnings;
	}
	/**
	 * 订单积分
     * @return 获取orderEarnings属性值
     */
	public Integer getOrderEarnings() {
		return this.orderEarnings;	
	}
	
	/**
	 * 订单号
	 * @param orderNo 设置 orderNo 属性值为参数值 orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo == null ? null : orderNo.trim();
	}
	/**
	 * 订单号
     * @return 获取orderNo属性值
     */
	public String getOrderNo() {
		return this.orderNo;	
	}
	
	/**
	 * 订单状态
	 * @param orderState 设置 orderState 属性值为参数值 orderState
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * 订单状态
     * @return 获取orderState属性值
     */
	public Integer getOrderState() {
		return this.orderState;	
	}
	
	/**
	 * 创建者ID
	 * @param creatorId 设置 creatorId 属性值为参数值 creatorId
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId == null ? null : creatorId.trim();
	}
	/**
	 * 创建者ID
     * @return 获取creatorId属性值
     */
	public String getCreatorId() {
		return this.creatorId;	
	}
	
	/**
	 * 创建人名称
	 * @param creatorName 设置 creatorName 属性值为参数值 creatorName
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName == null ? null : creatorName.trim();
	}
	/**
	 * 创建人名称
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
	 * 修改者ID
	 * @param modifyId 设置 modifyId 属性值为参数值 modifyId
	 */
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId == null ? null : modifyId.trim();
	}
	/**
	 * 修改者ID
     * @return 获取modifyId属性值
     */
	public String getModifyId() {
		return this.modifyId;	
	}
	
	/**
	 * 修改者名称
	 * @param modifyName 设置 modifyName 属性值为参数值 modifyName
	 */
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName == null ? null : modifyName.trim();
	}
	/**
	 * 修改者名称
     * @return 获取modifyName属性值
     */
	public String getModifyName() {
		return this.modifyName;	
	}
	
	/**
	 * 上次修改时间
	 * @param modifyTime 设置 modifyTime 属性值为参数值 modifyTime
	 */
	public void setModifyTime(java.sql.Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 上次修改时间
     * @return 获取modifyTime属性值
     */
	public java.sql.Timestamp getModifyTime() {
		return this.modifyTime;	
	}
	
	/**
	 * 地址
	 * @param address 设置 address 属性值为参数值 address
	 */
	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}
	/**
	 * 地址
     * @return 获取address属性值
     */
	public String getAddress() {
		return this.address;	
	}
	
	/**
	 * 用户姓名
	 * @param accountName 设置 accountName 属性值为参数值 accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}
	/**
	 * 用户姓名
     * @return 获取accountName属性值
     */
	public String getAccountName() {
		return this.accountName;	
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
	 * @return orderSons
	 */
	public List<DrtShopOrderDetail> getOrderSons() {
		return orderSons;
	}
	/**
	 * @param orderSons 要设置的 orderSons
	 */
	public void setOrderSons(List<DrtShopOrderDetail> orderSons) {
		this.orderSons = orderSons;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
}
	

	
