package com.csg.personcenter.entity.mybatis;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Description: 登录账户信息对象模型 Company: Syni
 * 
 * @version 1.0
 * @author 杨彬俊
 * @since 2017-11-01
 */
public class DrtAccount implements Serializable {
	
	private static final long serialVersionUID = -8366929034564774130L;

	public DrtAccount() {

	}

	private String sessionId;
	private String accountId;//主键ID
	private String userId;//外键ID
	private Timestamp createTime;//创建时间
	private String phone;//手机号
	private String pwd;//登录密码
	private String gesturePwd;//手势密码
	private String payPwd; //支付密码（添加新字段 by仙龙）
	private Integer accountStatus;//状态,0:正常,1:异常,2:锁定
	private String imgURL;//头像地址
	private String nickname;//昵称
	private Integer loginFailCnt;//登录失败次数
	private Timestamp loginFailTime;//登录失败时间
	private String remark;//备注
	private Integer gestureFailCnt;//修改手势密码错误次数
	private Timestamp gestureFailTime;//修改手势密码错误时间
	private String openSysMsg;//是否开启系统消息通知（0不开启，1开启）
	private Timestamp openSysMsgTime;//开启系统信息通知时间
	private String openEleBill;//是否开启电费账单消息提醒（0不开启，1开启）
	private String source;//来源
	private Timestamp openEleBillTime;//开启电费账单信息提醒时间
	private Integer totalEarnings;//总积分
	
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGesturePwd() {
		return gesturePwd;
	}

	public void setGesturePwd(String gesturePwd) {
		this.gesturePwd = gesturePwd;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}


	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getLoginFailCnt() {
		return loginFailCnt;
	}

	public void setLoginFailCnt(Integer loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}

	public Timestamp getLoginFailTime() {
		return loginFailTime;
	}

	public void setLoginFailTime(Timestamp loginFailTime) {
		this.loginFailTime = loginFailTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGestureFailCnt() {
		return gestureFailCnt;
	}

	public void setGestureFailCnt(Integer gestureFailCnt) {
		this.gestureFailCnt = gestureFailCnt;
	}

	public Timestamp getGestureFailTime() {
		return gestureFailTime;
	}

	public void setGestureFailTime(Timestamp gestureFailTime) {
		this.gestureFailTime = gestureFailTime;
	}

	public String getOpenSysMsg() {
		return openSysMsg;
	}

	public void setOpenSysMsg(String openSysMsg) {
		this.openSysMsg = openSysMsg;
	}

	public Timestamp getOpenSysMsgTime() {
		return openSysMsgTime;
	}

	public void setOpenSysMsgTime(Timestamp openSysMsgTime) {
		this.openSysMsgTime = openSysMsgTime;
	}

	public String getOpenEleBill() {
		return openEleBill;
	}

	public void setOpenEleBill(String openEleBill) {
		this.openEleBill = openEleBill;
	}

	public Timestamp getOpenEleBillTime() {
		return openEleBillTime;
	}

	public void setOpenEleBillTime(Timestamp openEleBillTime) {
		this.openEleBillTime = openEleBillTime;
	}

	public Integer getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(Integer totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
	
}
