package com.csg.personcenter.util.aliyun;

import java.io.Serializable;

/**
 * Description: 短信验证码对象模型
 * Company: Syni
 * @author 杨彬俊
 * @version 1.0
 * @since 2017-11-01
 */
public class ValidateMessage implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long createTime; //验证码创建时间
    private String validateNum; //验证码
    private int failedCount; //失败次数

    public ValidateMessage() {

    }

    public ValidateMessage(String validateNum) {
        this.createTime = System.currentTimeMillis();
        this.validateNum = validateNum;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getValidateNum() {
        return validateNum;
    }

    public void setValidateNum(String validateNum) {
        this.validateNum = validateNum;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }
}
