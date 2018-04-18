package com.csg.personcenter.entity;

/**
 * Description: 用电户信息变更对象模型
 * Company: Syni
 * @version 1.0
 * @author 刘恩希
 * @since 2017-12-18
 */
public class DrtElecUserView {

	/** 用电户号*/
	private String ydhh;
	/** 用电名称*/
	private String ydmc;
	/** 结算户号*/
	private String jshh;
	/** 用电地址*/
	private String yddz;
	/** 用户状态*/
	private String yhzt;
	/** 用电区域Id*/
	private String areaId;
	/** 用户手机号*/
	private String mobile;
	/** 绑定状态*/
	private String elecUserStatus;
	
	
	public String getYdhh() {
		return ydhh;
	}
	public void setYdhh(String ydhh) {
		this.ydhh = ydhh;
	}
	public String getYdmc() {
		return ydmc;
	}
	public void setYdmc(String ydmc) {
		this.ydmc = ydmc;
	}
	public String getJshh() {
		return jshh;
	}
	public void setJshh(String jshh) {
		this.jshh = jshh;
	}
	public String getYddz() {
		return yddz;
	}
	public void setYddz(String yddz) {
		this.yddz = yddz;
	}
	public String getYhzt() {
		return yhzt;
	}
	public void setYhzt(String yhzt) {
		this.yhzt = yhzt;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getElecUserStatus() {
		return elecUserStatus;
	}
	public void setElecUserStatus(String elecUserStatus) {
		this.elecUserStatus = elecUserStatus;
	}
	
    
}
