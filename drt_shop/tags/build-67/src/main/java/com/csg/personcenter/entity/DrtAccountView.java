package com.csg.personcenter.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 登录账户UI显示对象模型
 * Company: Syni
 * @version 1.0
 * @author 杨彬俊
 * @since 2017-11-02
 */
public class DrtAccountView implements Serializable {

  
	/**
	 * 
	 */
	private static final long serialVersionUID = -8711502574190297700L;
	private String accountId;//账户ID
    private String nickname;//昵称
    private String phone;//电话号码
    private String photoUrl;//图片链接
    private String enableGesPwd; //是否开启手势密码
    private String hasBankAccounts; //是否添加银行卡，0：未添加，1：已添加
    private String hasYDHH; //是否添加用电户号 0：未添加，1：已添加
    private String isAuth; //实名认证状态 0：未认证，1：认证中，2：认证成功，3：认证失败
    private String systemUnRead;//是否有系统未读消息 0为没有,大于0为未读消息条数
    private String billUnRead;//是否有电费账单未读消息 0为没有,大于0为未读消息条数
    private String unReadCount;//未读消息数量
    private String userName;//用户真实姓名
    private String idCard;//用户身份证号
    private String isPayPwd;//支付密码设置状态
    private String accountStatus;//账户状态
    private String isOpenPrePay;//该电融通用户是否有开启预购的用电户号
  	private String isFirstLoginTime;//是否是第一次登录
  	private List<DrtElecUserView> elecUserList;//绑定的用电户号
  	
    public DrtAccountView() {

    }

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getEnableGesPwd() {
		return enableGesPwd;
	}

	public void setEnableGesPwd(String enableGesPwd) {
		this.enableGesPwd = enableGesPwd;
	}

	public String getHasBankAccounts() {
		return hasBankAccounts;
	}

	public void setHasBankAccounts(String hasBankAccounts) {
		this.hasBankAccounts = hasBankAccounts;
	}

	public String getHasYDHH() {
		return hasYDHH;
	}

	public void setHasYDHH(String hasYDHH) {
		this.hasYDHH = hasYDHH;
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}

	public String getSystemUnRead() {
		return systemUnRead;
	}

	public void setSystemUnRead(String systemUnRead) {
		this.systemUnRead = systemUnRead;
	}

	public String getBillUnRead() {
		return billUnRead;
	}

	public void setBillUnRead(String billUnRead) {
		this.billUnRead = billUnRead;
	}

	public String getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(String unReadCount) {
		this.unReadCount = unReadCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIsPayPwd() {
		return isPayPwd;
	}

	public void setIsPayPwd(String isPayPwd) {
		this.isPayPwd = isPayPwd;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getIsOpenPrePay() {
		return isOpenPrePay;
	}

	public void setIsOpenPrePay(String isOpenPrePay) {
		this.isOpenPrePay = isOpenPrePay;
	}

	public String getIsFirstLoginTime() {
		return isFirstLoginTime;
	}

	public void setIsFirstLoginTime(String isFirstLoginTime) {
		this.isFirstLoginTime = isFirstLoginTime;
	}

	public List<DrtElecUserView> getElecUserList() {
		return elecUserList;
	}

	public void setElecUserList(List<DrtElecUserView> elecUserList) {
		this.elecUserList = elecUserList;
	}

    
}
