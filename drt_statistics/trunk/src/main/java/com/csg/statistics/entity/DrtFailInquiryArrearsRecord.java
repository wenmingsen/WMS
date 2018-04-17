package com.csg.statistics.entity;

import java.sql.Timestamp;

/**
 * 查询欠费失败记录实体类
 * @author xian long 2018 03 29
 *
 */
public class DrtFailInquiryArrearsRecord {

	/**主键id**/
	private String inquiryArrearsRecordId;
	/**状态：0.未处理1.已处理**/
	private String status;
	/**处理结果**/
	private String result;
	/**电融通id**/
	private String accountId;
	/**用电户id**/
	private String elecUserId;
	/**创建时间**/
	private Timestamp createTime;
	/**状态修改时间**/
	private Timestamp changeTime;
	/**查询次数**/
	private Integer searchCount;
	/**查詢成功生成的欠费账单id**/
	private String tid;
	/**充值订单号**/
	private String rechargeNum;
	/**手机号**/
	private String phone;
	
	public String getInquiryArrearsRecordId() {
		return inquiryArrearsRecordId;
	}
	public void setInquiryArrearsRecordId(String inquiryArrearsRecordId) {
		this.inquiryArrearsRecordId = inquiryArrearsRecordId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getElecUserId() {
		return elecUserId;
	}
	public void setElecUserId(String elecUserId) {
		this.elecUserId = elecUserId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Timestamp changeTime) {
		this.changeTime = changeTime;
	}
	public Integer getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getRechargeNum() {
		return rechargeNum;
	}
	public void setRechargeNum(String rechargeNum) {
		this.rechargeNum = rechargeNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "DrtFailInquiryArrearsRecord [inquiryArrearsRecordId="
				+ inquiryArrearsRecordId + ", status=" + status + ", result="
				+ result + ", accountId=" + accountId + ", elecUserId="
				+ elecUserId + ", createTime=" + createTime + ", changeTime="
				+ changeTime + ", searchCount=" + searchCount + ", tid=" + tid
				+ ", rechargeNum=" + rechargeNum + ", phone=" + phone + "]";
	}
	
}
