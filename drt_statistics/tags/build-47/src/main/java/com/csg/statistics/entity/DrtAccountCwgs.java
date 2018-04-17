package com.csg.statistics.entity;

import java.sql.Timestamp;

/**
 * Description:账户信息（文件）表
 * Company: Syni
 * @author 李达才
 * @since 2017-12-1
 *
 */
public class DrtAccountCwgs {

	private String drtAccountId;//主键ID
	
	private String accountNumber;//帐号编号
	
	private String accountName;//账户名称
	
	private String clientNumber;//客户编号
	
	private String clientName;//客户名称
	
	private String standbyOne;//备用字段1
	
	private String standbyTwo;//备用字段2
	
	private Timestamp updateTime;//同步时间

	public String getDrtAccountId() {
		return drtAccountId;
	}

	public void setDrtAccountId(String drtAccountId) {
		this.drtAccountId = drtAccountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getStandbyOne() {
		return standbyOne;
	}

	public void setStandbyOne(String standbyOne) {
		this.standbyOne = standbyOne;
	}

	public String getStandbyTwo() {
		return standbyTwo;
	}

	public void setStandbyTwo(String standbyTwo) {
		this.standbyTwo = standbyTwo;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
}
