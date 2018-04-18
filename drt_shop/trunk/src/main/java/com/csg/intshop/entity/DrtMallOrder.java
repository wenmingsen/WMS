package com.csg.intshop.entity;

/**
 * drt_mall_order-->DrtMallOrder 积分商城订单主表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月29日 曾令鹏
 */
public class DrtMallOrder{
	
	/** 主键 */
	private String id;

	/** 订单编号 */
	private String orderCode;

	/** 电融通用户ID */
	private String accountId;

	/** 电融通用户昵称 */
	private String nickname;

	/** 下单时间：yyyyMMddHHmmss */
	private Long submitTime;

	/** 订单总金额（单位：分） */
	private Long orderTotalAmount;

	/** 订单总积分（单位：分） */
	private Long orderTotalEarnings;

	/** 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单 */
	private Integer orderState;

	/** 店铺编号 */
	private String storeCode;

	/** 店铺名称 */
	private String storeName;

	/** 业务活动ID */
	private String businessActivityId;

	/** 费用项目ID */
	private String expenseProjectId;

	/** 预算科目ID */
	private String budgetSubjectId;

	/** 数据区域 */
	private String dataArea;

	/** 报账人ID */
	private String reimbursePersonId;

	/** 报账人姓名 */
	private String reimbursePerson;

	/** 报账人手机 */
	private String reimburseMobile;

	/** 申购人ID */
	private String applyPeopleId;

	/** 申购人姓名 */
	private String applyPeople;

	/** 申购人手机 */
	private String applyMobile;

	/** 发票类型(2=增值税普通，3=增值税专用) */
	private Integer invoiceType;

	/** 发票抬头 */
	private String invoiceTitle;

	/** 配送方式（1=快递，2=EMS，3=平邮，4=商户自有物流） */
	private Integer deliveryType;

	/** 单位名称 */
	private String companyName;

	/** 发票内容 */
	private String invoiceContent;

	/** 收货人ID */
	private String consigneeId;

	/** 收货人姓名 */
	private String consigneeName;

	/** 收货人固定电话（固话或手机有1项必填） */
	private String consigneeTelephone;

	/** 收货人手机号码（固话或手机有1项必填） */
	private String consigneeMobile;

	/** 收货地址邮编 */
	private String consigneeZip;

	/** 收货人省份ID */
	private String consigneeProvinceId;

	/** 收货人省份名称 */
	private String consigneeProvinceName;

	/** 收货人城市ID */
	private String consigneeCityId;

	/** 收货人城市名称 */
	private String consigneeCityName;

	/** 收货人区县ID */
	private String consigneeCountyId;

	/** 收货人区县名称 */
	private String consigneeCountyName;

	/** 收货人镇ID */
	private String consigneeTownId;

	/** 收货人镇名称 */
	private String consigneeTownName;

	/** 收货人详细地址(地址需要省市区镇4级使用逗号分隔，4级城镇没有的时候使用null代替，示例：广东,深圳市,光明新区,null,公明街道振明路153号) */
	private String consigneeAddressDetail;

	/** 给卖家留言 */
	private String orderMemberMemo;

	/** 创建人ID */
	private String creatorId;

	/** 创建人姓名 */
	private String creatorName;

	/** 创建时间：yyyyMMddHHmmss */
	private Long createTime;

	/** 最后修改人ID */
	private String lastModifierId;

	/** 最后修改人 */
	private String lastModifierName;

	/** 最后修改时间：yyyyMMddHHmmss */
	private Long lastModifierTime;

	/** 是否删除：0-是 1-否 */
	private Integer isDelete;

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
	 * 订单编号
	 * @param orderCode 设置 orderCode 属性值为参数值 orderCode
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode == null ? null : orderCode.trim();
	}
	/**
	 * 订单编号
     * @return 获取orderCode属性值
     */
	public String getOrderCode() {
		return this.orderCode;	
	}
	
	/**
	 * 电融通用户ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 电融通用户ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 电融通用户昵称
	 * @param nickname 设置 nickname 属性值为参数值 nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}
	/**
	 * 电融通用户昵称
     * @return 获取nickname属性值
     */
	public String getNickname() {
		return this.nickname;	
	}
	
	/**
	 * 下单时间：yyyyMMddHHmmss
	 * @param submitTime 设置 submitTime 属性值为参数值 submitTime
	 */
	public void setSubmitTime(Long submitTime) {
		this.submitTime = submitTime;
	}
	/**
	 * 下单时间：yyyyMMddHHmmss
     * @return 获取submitTime属性值
     */
	public Long getSubmitTime() {
		return this.submitTime;	
	}
	
	/**
	 * 订单总金额（单位：分）
	 * @param orderTotalAmount 设置 orderTotalAmount 属性值为参数值 orderTotalAmount
	 */
	public void setOrderTotalAmount(Long orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	/**
	 * 订单总金额（单位：分）
     * @return 获取orderTotalAmount属性值
     */
	public Long getOrderTotalAmount() {
		return this.orderTotalAmount;	
	}
	
	/**
	 * 订单总积分（单位：分）
	 * @param orderTotalEarnings 设置 orderTotalEarnings 属性值为参数值 orderTotalEarnings
	 */
	public void setOrderTotalEarnings(Long orderTotalEarnings) {
		this.orderTotalEarnings = orderTotalEarnings;
	}
	/**
	 * 订单总积分（单位：分）
     * @return 获取orderTotalEarnings属性值
     */
	public Long getOrderTotalEarnings() {
		return this.orderTotalEarnings;	
	}
	
	/**
	 * 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
	 * @param orderState 设置 orderState 属性值为参数值 orderState
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
     * @return 获取orderState属性值
     */
	public Integer getOrderState() {
		return this.orderState;	
	}
	
	/**
	 * 店铺编号
	 * @param storeCode 设置 storeCode 属性值为参数值 storeCode
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
	}
	/**
	 * 店铺编号
     * @return 获取storeCode属性值
     */
	public String getStoreCode() {
		return this.storeCode;	
	}
	
	/**
	 * 店铺名称
	 * @param storeName 设置 storeName 属性值为参数值 storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName == null ? null : storeName.trim();
	}
	/**
	 * 店铺名称
     * @return 获取storeName属性值
     */
	public String getStoreName() {
		return this.storeName;	
	}
	
	/**
	 * 业务活动ID
	 * @param businessActivityId 设置 businessActivityId 属性值为参数值 businessActivityId
	 */
	public void setBusinessActivityId(String businessActivityId) {
		this.businessActivityId = businessActivityId == null ? null : businessActivityId.trim();
	}
	/**
	 * 业务活动ID
     * @return 获取businessActivityId属性值
     */
	public String getBusinessActivityId() {
		return this.businessActivityId;	
	}
	
	/**
	 * 费用项目ID
	 * @param expenseProjectId 设置 expenseProjectId 属性值为参数值 expenseProjectId
	 */
	public void setExpenseProjectId(String expenseProjectId) {
		this.expenseProjectId = expenseProjectId == null ? null : expenseProjectId.trim();
	}
	/**
	 * 费用项目ID
     * @return 获取expenseProjectId属性值
     */
	public String getExpenseProjectId() {
		return this.expenseProjectId;	
	}
	
	/**
	 * 预算科目ID
	 * @param budgetSubjectId 设置 budgetSubjectId 属性值为参数值 budgetSubjectId
	 */
	public void setBudgetSubjectId(String budgetSubjectId) {
		this.budgetSubjectId = budgetSubjectId == null ? null : budgetSubjectId.trim();
	}
	/**
	 * 预算科目ID
     * @return 获取budgetSubjectId属性值
     */
	public String getBudgetSubjectId() {
		return this.budgetSubjectId;	
	}
	
	/**
	 * 数据区域
	 * @param dataArea 设置 dataArea 属性值为参数值 dataArea
	 */
	public void setDataArea(String dataArea) {
		this.dataArea = dataArea == null ? null : dataArea.trim();
	}
	/**
	 * 数据区域
     * @return 获取dataArea属性值
     */
	public String getDataArea() {
		return this.dataArea;	
	}
	
	/**
	 * 报账人ID
	 * @param reimbursePersonId 设置 reimbursePersonId 属性值为参数值 reimbursePersonId
	 */
	public void setReimbursePersonId(String reimbursePersonId) {
		this.reimbursePersonId = reimbursePersonId == null ? null : reimbursePersonId.trim();
	}
	/**
	 * 报账人ID
     * @return 获取reimbursePersonId属性值
     */
	public String getReimbursePersonId() {
		return this.reimbursePersonId;	
	}
	
	/**
	 * 报账人姓名
	 * @param reimbursePerson 设置 reimbursePerson 属性值为参数值 reimbursePerson
	 */
	public void setReimbursePerson(String reimbursePerson) {
		this.reimbursePerson = reimbursePerson == null ? null : reimbursePerson.trim();
	}
	/**
	 * 报账人姓名
     * @return 获取reimbursePerson属性值
     */
	public String getReimbursePerson() {
		return this.reimbursePerson;	
	}
	
	/**
	 * 报账人手机
	 * @param reimburseMobile 设置 reimburseMobile 属性值为参数值 reimburseMobile
	 */
	public void setReimburseMobile(String reimburseMobile) {
		this.reimburseMobile = reimburseMobile == null ? null : reimburseMobile.trim();
	}
	/**
	 * 报账人手机
     * @return 获取reimburseMobile属性值
     */
	public String getReimburseMobile() {
		return this.reimburseMobile;	
	}
	
	/**
	 * 申购人ID
	 * @param applyPeopleId 设置 applyPeopleId 属性值为参数值 applyPeopleId
	 */
	public void setApplyPeopleId(String applyPeopleId) {
		this.applyPeopleId = applyPeopleId == null ? null : applyPeopleId.trim();
	}
	/**
	 * 申购人ID
     * @return 获取applyPeopleId属性值
     */
	public String getApplyPeopleId() {
		return this.applyPeopleId;	
	}
	
	/**
	 * 申购人姓名
	 * @param applyPeople 设置 applyPeople 属性值为参数值 applyPeople
	 */
	public void setApplyPeople(String applyPeople) {
		this.applyPeople = applyPeople == null ? null : applyPeople.trim();
	}
	/**
	 * 申购人姓名
     * @return 获取applyPeople属性值
     */
	public String getApplyPeople() {
		return this.applyPeople;	
	}
	
	/**
	 * 申购人手机
	 * @param applyMobile 设置 applyMobile 属性值为参数值 applyMobile
	 */
	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile == null ? null : applyMobile.trim();
	}
	/**
	 * 申购人手机
     * @return 获取applyMobile属性值
     */
	public String getApplyMobile() {
		return this.applyMobile;	
	}
	
	/**
	 * 发票类型(2=增值税普通，3=增值税专用)
	 * @param invoiceType 设置 invoiceType 属性值为参数值 invoiceType
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 发票类型(2=增值税普通，3=增值税专用)
     * @return 获取invoiceType属性值
     */
	public Integer getInvoiceType() {
		return this.invoiceType;	
	}
	
	/**
	 * 发票抬头
	 * @param invoiceTitle 设置 invoiceTitle 属性值为参数值 invoiceTitle
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
	}
	/**
	 * 发票抬头
     * @return 获取invoiceTitle属性值
     */
	public String getInvoiceTitle() {
		return this.invoiceTitle;	
	}
	
	/**
	 * 配送方式（1=快递，2=EMS，3=平邮，4=商户自有物流）
	 * @param deliveryType 设置 deliveryType 属性值为参数值 deliveryType
	 */
	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}
	/**
	 * 配送方式（1=快递，2=EMS，3=平邮，4=商户自有物流）
     * @return 获取deliveryType属性值
     */
	public Integer getDeliveryType() {
		return this.deliveryType;	
	}
	
	/**
	 * 单位名称
	 * @param companyName 设置 companyName 属性值为参数值 companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}
	/**
	 * 单位名称
     * @return 获取companyName属性值
     */
	public String getCompanyName() {
		return this.companyName;	
	}
	
	/**
	 * 发票内容
	 * @param invoiceContent 设置 invoiceContent 属性值为参数值 invoiceContent
	 */
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
	}
	/**
	 * 发票内容
     * @return 获取invoiceContent属性值
     */
	public String getInvoiceContent() {
		return this.invoiceContent;	
	}
	
	/**
	 * 收货人ID
	 * @param consigneeId 设置 consigneeId 属性值为参数值 consigneeId
	 */
	public void setConsigneeId(String consigneeId) {
		this.consigneeId = consigneeId == null ? null : consigneeId.trim();
	}
	/**
	 * 收货人ID
     * @return 获取consigneeId属性值
     */
	public String getConsigneeId() {
		return this.consigneeId;	
	}
	
	/**
	 * 收货人姓名
	 * @param consigneeName 设置 consigneeName 属性值为参数值 consigneeName
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName == null ? null : consigneeName.trim();
	}
	/**
	 * 收货人姓名
     * @return 获取consigneeName属性值
     */
	public String getConsigneeName() {
		return this.consigneeName;	
	}
	
	/**
	 * 收货人固定电话（固话或手机有1项必填）
	 * @param consigneeTelephone 设置 consigneeTelephone 属性值为参数值 consigneeTelephone
	 */
	public void setConsigneeTelephone(String consigneeTelephone) {
		this.consigneeTelephone = consigneeTelephone == null ? null : consigneeTelephone.trim();
	}
	/**
	 * 收货人固定电话（固话或手机有1项必填）
     * @return 获取consigneeTelephone属性值
     */
	public String getConsigneeTelephone() {
		return this.consigneeTelephone;	
	}
	
	/**
	 * 收货人手机号码（固话或手机有1项必填）
	 * @param consigneeMobile 设置 consigneeMobile 属性值为参数值 consigneeMobile
	 */
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
	}
	/**
	 * 收货人手机号码（固话或手机有1项必填）
     * @return 获取consigneeMobile属性值
     */
	public String getConsigneeMobile() {
		return this.consigneeMobile;	
	}
	
	/**
	 * 收货地址邮编
	 * @param consigneeZip 设置 consigneeZip 属性值为参数值 consigneeZip
	 */
	public void setConsigneeZip(String consigneeZip) {
		this.consigneeZip = consigneeZip == null ? null : consigneeZip.trim();
	}
	/**
	 * 收货地址邮编
     * @return 获取consigneeZip属性值
     */
	public String getConsigneeZip() {
		return this.consigneeZip;	
	}
	
	/**
	 * 收货人省份ID
	 * @param consigneeProvinceId 设置 consigneeProvinceId 属性值为参数值 consigneeProvinceId
	 */
	public void setConsigneeProvinceId(String consigneeProvinceId) {
		this.consigneeProvinceId = consigneeProvinceId == null ? null : consigneeProvinceId.trim();
	}
	/**
	 * 收货人省份ID
     * @return 获取consigneeProvinceId属性值
     */
	public String getConsigneeProvinceId() {
		return this.consigneeProvinceId;	
	}
	
	/**
	 * 收货人省份名称
	 * @param consigneeProvinceName 设置 consigneeProvinceName 属性值为参数值 consigneeProvinceName
	 */
	public void setConsigneeProvinceName(String consigneeProvinceName) {
		this.consigneeProvinceName = consigneeProvinceName == null ? null : consigneeProvinceName.trim();
	}
	/**
	 * 收货人省份名称
     * @return 获取consigneeProvinceName属性值
     */
	public String getConsigneeProvinceName() {
		return this.consigneeProvinceName;	
	}
	
	/**
	 * 收货人城市ID
	 * @param consigneeCityId 设置 consigneeCityId 属性值为参数值 consigneeCityId
	 */
	public void setConsigneeCityId(String consigneeCityId) {
		this.consigneeCityId = consigneeCityId == null ? null : consigneeCityId.trim();
	}
	/**
	 * 收货人城市ID
     * @return 获取consigneeCityId属性值
     */
	public String getConsigneeCityId() {
		return this.consigneeCityId;	
	}
	
	/**
	 * 收货人城市名称
	 * @param consigneeCityName 设置 consigneeCityName 属性值为参数值 consigneeCityName
	 */
	public void setConsigneeCityName(String consigneeCityName) {
		this.consigneeCityName = consigneeCityName == null ? null : consigneeCityName.trim();
	}
	/**
	 * 收货人城市名称
     * @return 获取consigneeCityName属性值
     */
	public String getConsigneeCityName() {
		return this.consigneeCityName;	
	}
	
	/**
	 * 收货人区县ID
	 * @param consigneeCountyId 设置 consigneeCountyId 属性值为参数值 consigneeCountyId
	 */
	public void setConsigneeCountyId(String consigneeCountyId) {
		this.consigneeCountyId = consigneeCountyId == null ? null : consigneeCountyId.trim();
	}
	/**
	 * 收货人区县ID
     * @return 获取consigneeCountyId属性值
     */
	public String getConsigneeCountyId() {
		return this.consigneeCountyId;	
	}
	
	/**
	 * 收货人区县名称
	 * @param consigneeCountyName 设置 consigneeCountyName 属性值为参数值 consigneeCountyName
	 */
	public void setConsigneeCountyName(String consigneeCountyName) {
		this.consigneeCountyName = consigneeCountyName == null ? null : consigneeCountyName.trim();
	}
	/**
	 * 收货人区县名称
     * @return 获取consigneeCountyName属性值
     */
	public String getConsigneeCountyName() {
		return this.consigneeCountyName;	
	}
	
	/**
	 * 收货人镇ID
	 * @param consigneeTownId 设置 consigneeTownId 属性值为参数值 consigneeTownId
	 */
	public void setConsigneeTownId(String consigneeTownId) {
		this.consigneeTownId = consigneeTownId == null ? null : consigneeTownId.trim();
	}
	/**
	 * 收货人镇ID
     * @return 获取consigneeTownId属性值
     */
	public String getConsigneeTownId() {
		return this.consigneeTownId;	
	}
	
	/**
	 * 收货人镇名称
	 * @param consigneeTownName 设置 consigneeTownName 属性值为参数值 consigneeTownName
	 */
	public void setConsigneeTownName(String consigneeTownName) {
		this.consigneeTownName = consigneeTownName == null ? null : consigneeTownName.trim();
	}
	/**
	 * 收货人镇名称
     * @return 获取consigneeTownName属性值
     */
	public String getConsigneeTownName() {
		return this.consigneeTownName;	
	}
	
	/**
	 * 收货人详细地址(地址需要省市区镇4级使用逗号分隔，4级城镇没有的时候使用null代替，示例：广东,深圳市,光明新区,null,公明街道振明路153号)
	 * @param consigneeAddressDetail 设置 consigneeAddressDetail 属性值为参数值 consigneeAddressDetail
	 */
	public void setConsigneeAddressDetail(String consigneeAddressDetail) {
		this.consigneeAddressDetail = consigneeAddressDetail == null ? null : consigneeAddressDetail.trim();
	}
	/**
	 * 收货人详细地址(地址需要省市区镇4级使用逗号分隔，4级城镇没有的时候使用null代替，示例：广东,深圳市,光明新区,null,公明街道振明路153号)
     * @return 获取consigneeAddressDetail属性值
     */
	public String getConsigneeAddressDetail() {
		return this.consigneeAddressDetail;	
	}
	
	/**
	 * 给卖家留言
	 * @param orderMemberMemo 设置 orderMemberMemo 属性值为参数值 orderMemberMemo
	 */
	public void setOrderMemberMemo(String orderMemberMemo) {
		this.orderMemberMemo = orderMemberMemo == null ? null : orderMemberMemo.trim();
	}
	/**
	 * 给卖家留言
     * @return 获取orderMemberMemo属性值
     */
	public String getOrderMemberMemo() {
		return this.orderMemberMemo;	
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
	 * 创建时间：yyyyMMddHHmmss
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建时间：yyyyMMddHHmmss
     * @return 获取createTime属性值
     */
	public Long getCreateTime() {
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
	 * 最后修改时间：yyyyMMddHHmmss
	 * @param lastModifierTime 设置 lastModifierTime 属性值为参数值 lastModifierTime
	 */
	public void setLastModifierTime(Long lastModifierTime) {
		this.lastModifierTime = lastModifierTime;
	}
	/**
	 * 最后修改时间：yyyyMMddHHmmss
     * @return 获取lastModifierTime属性值
     */
	public Long getLastModifierTime() {
		return this.lastModifierTime;	
	}
	
	/**
	 * 是否删除：0-是 1-否
	 * @param isDelete 设置 isDelete 属性值为参数值 isDelete
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 是否删除：0-是 1-否
     * @return 获取isDelete属性值
     */
	public Integer getIsDelete() {
		return this.isDelete;	
	}
	@Override
	public String toString() {
		return "DrtMallOrder [id=" + id + ", orderCode=" + orderCode
				+ ", accountId=" + accountId + ", nickname=" + nickname
				+ ", submitTime=" + submitTime + ", orderTotalAmount="
				+ orderTotalAmount + ", orderTotalEarnings="
				+ orderTotalEarnings + ", orderState=" + orderState
				+ ", storeCode=" + storeCode + ", storeName=" + storeName
				+ ", businessActivityId=" + businessActivityId
				+ ", expenseProjectId=" + expenseProjectId
				+ ", budgetSubjectId=" + budgetSubjectId + ", dataArea="
				+ dataArea + ", reimbursePersonId=" + reimbursePersonId
				+ ", reimbursePerson=" + reimbursePerson + ", reimburseMobile="
				+ reimburseMobile + ", applyPeopleId=" + applyPeopleId
				+ ", applyPeople=" + applyPeople + ", applyMobile="
				+ applyMobile + ", invoiceType=" + invoiceType
				+ ", invoiceTitle=" + invoiceTitle + ", deliveryType="
				+ deliveryType + ", companyName=" + companyName
				+ ", invoiceContent=" + invoiceContent + ", consigneeId="
				+ consigneeId + ", consigneeName=" + consigneeName
				+ ", consigneeTelephone=" + consigneeTelephone
				+ ", consigneeMobile=" + consigneeMobile + ", consigneeZip="
				+ consigneeZip + ", consigneeProvinceId=" + consigneeProvinceId
				+ ", consigneeProvinceName=" + consigneeProvinceName
				+ ", consigneeCityId=" + consigneeCityId
				+ ", consigneeCityName=" + consigneeCityName
				+ ", consigneeCountyId=" + consigneeCountyId
				+ ", consigneeCountyName=" + consigneeCountyName
				+ ", consigneeTownId=" + consigneeTownId
				+ ", consigneeTownName=" + consigneeTownName
				+ ", consigneeAddressDetail=" + consigneeAddressDetail
				+ ", orderMemberMemo=" + orderMemberMemo + ", creatorId="
				+ creatorId + ", creatorName=" + creatorName + ", createTime="
				+ createTime + ", lastModifierId=" + lastModifierId
				+ ", lastModifierName=" + lastModifierName
				+ ", lastModifierTime=" + lastModifierTime + ", isDelete="
				+ isDelete + "]";
	}
	
	
	
}
	

	
