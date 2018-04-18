package com.csg.personcenter.entity.mybatis;

import java.io.Serializable;
import java.sql.Timestamp;

public class DrtAccountIdCard implements Serializable {

	private static final long serialVersionUID = 7865242962000470946L;
	private String idCardId;
	private String accountId;
	private String username;
	private String idCard;
	private String cardPicUrl1;
	private String cardPicUrl2;
	private String authStatus;
	private Timestamp authCreateTime;
	private Timestamp authResultTime;
	
	public DrtAccountIdCard(){
	}
	
	public String getIdCardId() {
		return idCardId;
	}
	public void setIdCardId(String idCardId) {
		this.idCardId = idCardId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCardPicUrl1() {
		return cardPicUrl1;
	}
	public void setCardPicUrl1(String cardPicUrl1) {
		this.cardPicUrl1 = cardPicUrl1;
	}
	public String getCardPicUrl2() {
		return cardPicUrl2;
	}
	public void setCardPicUrl2(String cardPicUrl2) {
		this.cardPicUrl2 = cardPicUrl2;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public Timestamp getAuthCreateTime() {
		return authCreateTime;
	}
	public void setAuthCreateTime(Timestamp authCreateTime) {
		this.authCreateTime = authCreateTime;
	}
	public Timestamp getAuthResultTime() {
		return authResultTime;
	}
	public void setAuthResultTime(Timestamp authResultTime) {
		this.authResultTime = authResultTime;
	}
	
	
}
