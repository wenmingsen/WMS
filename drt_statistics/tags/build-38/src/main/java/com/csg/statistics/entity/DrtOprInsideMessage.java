package com.csg.statistics.entity;

/**
 * drt_opr_inside_message-->DrtOprInsideMessage 站内消息表
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月06日 温明森
 */
public class DrtOprInsideMessage{
	
	/** 主键 */
	private String id;

	/** 站内消息内容 */
	private String message;

	/** 站内消息名称 */
	private String messageName;

	/** 站内消息状态(0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕) */
	private Integer messageState;

	/** 发送类型（1-定时 2- 循环） */
	private Integer sendType;

	/** 发送频率（1-秒 2-分 3-小时 4-天 5- 月 6-年） */
	private Integer sendRate;

	/** 发送时间 */
	private Long sendTime;

	/** 发送开始时间 */
	private Long startTime;

	/** 发送结束时间 */
	private Long endTime;

	/** 是否删除 */
	private Integer isDelete;

	/** 审批状态 */
	private Integer auditState;

	/** 创建时间 */
	private Long createTime;

	/** 创建人ID */
	private String creatorId;

	/** 创建人名称 */
	private String creatorName;

	/** 修改时间 */
	private Long modifyTime;

	/** 修改人ID */
	private String modifyId;

	/** 修改人名称 */
	private String modifyName;

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
	 * 站内消息内容
	 * @param message 设置 message 属性值为参数值 message
	 */
	public void setMessage(String message) {
		this.message = message == null ? null : message.trim();
	}
	/**
	 * 站内消息内容
     * @return 获取message属性值
     */
	public String getMessage() {
		return this.message;	
	}
	
	/**
	 * 站内消息名称
	 * @param messageName 设置 messageName 属性值为参数值 messageName
	 */
	public void setMessageName(String messageName) {
		this.messageName = messageName == null ? null : messageName.trim();
	}
	/**
	 * 站内消息名称
     * @return 获取messageName属性值
     */
	public String getMessageName() {
		return this.messageName;	
	}
	
	/**
	 * 站内消息状态(0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕)
	 * @param messageState 设置 messageState 属性值为参数值 messageState
	 */
	public void setMessageState(Integer messageState) {
		this.messageState = messageState;
	}
	/**
	 * 站内消息状态(0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕)
     * @return 获取messageState属性值
     */
	public Integer getMessageState() {
		return this.messageState;	
	}
	
	/**
	 * 发送类型（1-定时 2- 循环）
	 * @param sendType 设置 sendType 属性值为参数值 sendType
	 */
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	/**
	 * 发送类型（1-定时 2- 循环）
     * @return 获取sendType属性值
     */
	public Integer getSendType() {
		return this.sendType;	
	}
	
	/**
	 * 发送频率（1-秒 2-分 3-小时 4-天 5- 月 6-年）
	 * @param sendRate 设置 sendRate 属性值为参数值 sendRate
	 */
	public void setSendRate(Integer sendRate) {
		this.sendRate = sendRate;
	}
	/**
	 * 发送频率（1-秒 2-分 3-小时 4-天 5- 月 6-年）
     * @return 获取sendRate属性值
     */
	public Integer getSendRate() {
		return this.sendRate;	
	}
	
	/**
	 * 发送时间
	 * @param sendTime 设置 sendTime 属性值为参数值 sendTime
	 */
	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * 发送时间
     * @return 获取sendTime属性值
     */
	public Long getSendTime() {
		return this.sendTime;	
	}
	
	/**
	 * 发送开始时间
	 * @param startTime 设置 startTime 属性值为参数值 startTime
	 */
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	/**
	 * 发送开始时间
     * @return 获取startTime属性值
     */
	public Long getStartTime() {
		return this.startTime;	
	}
	
	/**
	 * 发送结束时间
	 * @param endTime 设置 endTime 属性值为参数值 endTime
	 */
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	/**
	 * 发送结束时间
     * @return 获取endTime属性值
     */
	public Long getEndTime() {
		return this.endTime;	
	}
	
	/**
	 * 是否删除
	 * @param isDelete 设置 isDelete 属性值为参数值 isDelete
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 是否删除
     * @return 获取isDelete属性值
     */
	public Integer getIsDelete() {
		return this.isDelete;	
	}
	
	/**
	 * 审批状态
	 * @param auditState 设置 auditState 属性值为参数值 auditState
	 */
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	/**
	 * 审批状态
     * @return 获取auditState属性值
     */
	public Integer getAuditState() {
		return this.auditState;	
	}
	
	/**
	 * 创建时间
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建时间
     * @return 获取createTime属性值
     */
	public Long getCreateTime() {
		return this.createTime;	
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
	 * 修改时间
	 * @param modifyTime 设置 modifyTime 属性值为参数值 modifyTime
	 */
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	/**
	 * 修改时间
     * @return 获取modifyTime属性值
     */
	public Long getModifyTime() {
		return this.modifyTime;	
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
	 * 修改人名称
	 * @param modifyName 设置 modifyName 属性值为参数值 modifyName
	 */
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName == null ? null : modifyName.trim();
	}
	/**
	 * 修改人名称
     * @return 获取modifyName属性值
     */
	public String getModifyName() {
		return this.modifyName;	
	}
	
	
}
	

	
