
package com.csg.statistics.entity;

/**
 * drt_monitor_points_day-->DrtMonitorPointsDay 日积分监控表
 *
 * @author 惠新宇
 * @since 1.8
 * @version 2018年01月07日
 */
public class DrtMonitorPointsDay {
    
    /** 主键 */
    private String id;
    
    /** 当前日期：yyyyMMdd */
    private Integer presentDate;
    
    /** 用户消费积分数（总） */
    private Integer consumeEarnings;
    
    /** 用户消费积分数（最大） */
    private Integer consumeEarningsMax;
    
    /** 用户消费积分数（最小） */
    private Integer consumeEarningsMin;
    
    /** 过去一周消费积分数是第一档的用户数（1k以下） */
    private Integer consumeLevel1WeekNum;
    
    /** 过去一周消费积分数是第二档的用户数（1k-5k） */
    private Integer consumeLevel2WeekNum;
    
    /** 过去一周消费积分数是第三档的用户数（5k-10k） */
    private Integer consumeLevel3WeekNum;
    
    /** 过去一周消费积分数是第四档的用户数（10k以上） */
    private Integer consumeLevel4WeekNum;
    
    /** 用户产生积分数收益型（总） */
    private Integer proceedsEarnings;
    
    /** 用户产生积分数收益型（最大） */
    private Integer proceedsEarningsMax;
    
    /** 用户产生积分数收益型（最小） */
    private Integer proceedsEarningsMin;
    
    /** 过去一周产生积分数(收益型)是第一档的用户数（1k以下） */
    private Integer proceedsLevel1WeekNum;
    
    /** 过去一周产生积分数(收益型)是第二档的用户数（1k-5k） */
    private Integer proceedsLevel2WeekNum;
    
    /** 过去一周产生积分数(收益型)是第三档的用户数（5k-10k） */
    private Integer proceedsLevel3WeekNum;
    
    /** 过去一周产生积分数(收益型)是第四档的用户数（10k以上） */
    private Integer proceedsLevel4WeekNum;
    
    /** 用户产生积分数推广型（总） */
    private Integer spreadEarnings;
    
    /** 用户产生积分数推广型（最大） */
    private Integer spreadEarningsMax;
    
    /** 用户产生积分数推广型（最小） */
    private Integer spreadEarningsMin;
    
    /** 过去一周产生积分数(推广型)是第一档的用户数（1k以下） */
    private Integer spreadLevel1WeekNum;
    
    /** 过去一周产生积分数(推广型)是第二档的用户数（1k-5k） */
    private Integer spreadLevel2WeekNum;
    
    /** 过去一周产生积分数(推广型)是第三档的用户数（5k-10k） */
    private Integer spreadLevel3WeekNum;
    
    /** 过去一周产生积分数(推广型)是第四档的用户数（10k以上） */
    private Integer spreadLevel4WeekNum;
    
    /** 用户产生积分数消费型（总） */
    private Integer paymentEarnings;
    
    /** 用户产生积分数消费型（最大） */
    private Integer paymentEarningsMax;
    
    /** 用户产生积分数消费型（最小） */
    private Integer paymentEarningsMin;
    
    /** 过去一周产生积分数(消费型)是第一档的用户数（1k以下） */
    private Integer paymentLevel1WeekNum;
    
    /** 过去一周产生积分数(消费型)是第二档的用户数（1k-5k） */
    private Integer paymentLevel2WeekNum;
    
    /** 过去一周产生积分数(消费型)是第三档的用户数（5k-10k） */
    private Integer paymentLevel3WeekNum;
    
    /** 过去一周产生积分数(消费型)是第四档的用户数（10k以上） */
    private Integer paymentLevel4WeekNum;
    
    /** 过去一周剩余积分是第一档的用户数（1k以下） */
    private Integer remainLevel1WeekNum;
    
    /** 过去一周剩余积分是第二档的用户数（1k-5k） */
    private Integer remainLevel2WeekNum;
    
    /** 过去一周剩余积分)是第三档的用户数（5k-10k） */
    private Integer remainLevel3WeekNum;
    
    /** 过去一周剩余积分是第四档的用户数（10k以上） */
    private Integer remainLevel4WeekNum;
    
    /** 剩余积分 */
    private Integer remainEarnings;
    
    /** 剩余积分_最大值 */
    private Integer remainEarningsMax;
    
    /** 剩余积分_最小值 */
    private Integer remainEarningsMin;
    
    /**
     * 主键
     * 
     * @param id 设置 id 属性值为参数值 id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    /**
     * 主键
     * 
     * @return 获取id属性值
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * 当前日期：yyyyMMdd
     * 
     * @param presentDate 设置 presentDate 属性值为参数值 presentDate
     */
    public void setPresentDate(Integer presentDate) {
        this.presentDate = presentDate;
    }
    
    /**
     * 当前日期：yyyyMMdd
     * 
     * @return 获取presentDate属性值
     */
    public Integer getPresentDate() {
        return this.presentDate;
    }
    
    /**
     * 用户消费积分数（总）
     * 
     * @param consumeEarnings 设置 consumeEarnings 属性值为参数值 consumeEarnings
     */
    public void setConsumeEarnings(Integer consumeEarnings) {
        this.consumeEarnings = consumeEarnings;
    }
    
    /**
     * 用户消费积分数（总）
     * 
     * @return 获取consumeEarnings属性值
     */
    public Integer getConsumeEarnings() {
        return this.consumeEarnings;
    }
    
    /**
     * 用户消费积分数（最大）
     * 
     * @param consumeEarningsMax 设置 consumeEarningsMax 属性值为参数值 consumeEarningsMax
     */
    public void setConsumeEarningsMax(Integer consumeEarningsMax) {
        this.consumeEarningsMax = consumeEarningsMax;
    }
    
    /**
     * 用户消费积分数（最大）
     * 
     * @return 获取consumeEarningsMax属性值
     */
    public Integer getConsumeEarningsMax() {
        return this.consumeEarningsMax;
    }
    
    /**
     * 用户消费积分数（最小）
     * 
     * @param consumeEarningsMin 设置 consumeEarningsMin 属性值为参数值 consumeEarningsMin
     */
    public void setConsumeEarningsMin(Integer consumeEarningsMin) {
        this.consumeEarningsMin = consumeEarningsMin;
    }
    
    /**
     * 用户消费积分数（最小）
     * 
     * @return 获取consumeEarningsMin属性值
     */
    public Integer getConsumeEarningsMin() {
        return this.consumeEarningsMin;
    }
    
    /**
     * 过去一周消费积分数是第一档的用户数（1k以下）
     * 
     * @param consumeLevel1WeekNum 设置 consumeLevel1WeekNum 属性值为参数值 consumeLevel1WeekNum
     */
    public void setConsumeLevel1WeekNum(Integer consumeLevel1WeekNum) {
        this.consumeLevel1WeekNum = consumeLevel1WeekNum;
    }
    
    /**
     * 过去一周消费积分数是第一档的用户数（1k以下）
     * 
     * @return 获取consumeLevel1WeekNum属性值
     */
    public Integer getConsumeLevel1WeekNum() {
        return this.consumeLevel1WeekNum;
    }
    
    /**
     * 过去一周消费积分数是第二档的用户数（1k-5k）
     * 
     * @param consumeLevel2WeekNum 设置 consumeLevel2WeekNum 属性值为参数值 consumeLevel2WeekNum
     */
    public void setConsumeLevel2WeekNum(Integer consumeLevel2WeekNum) {
        this.consumeLevel2WeekNum = consumeLevel2WeekNum;
    }
    
    /**
     * 过去一周消费积分数是第二档的用户数（1k-5k）
     * 
     * @return 获取consumeLevel2WeekNum属性值
     */
    public Integer getConsumeLevel2WeekNum() {
        return this.consumeLevel2WeekNum;
    }
    
    /**
     * 过去一周消费积分数是第三档的用户数（5k-10k）
     * 
     * @param consumeLevel3WeekNum 设置 consumeLevel3WeekNum 属性值为参数值 consumeLevel3WeekNum
     */
    public void setConsumeLevel3WeekNum(Integer consumeLevel3WeekNum) {
        this.consumeLevel3WeekNum = consumeLevel3WeekNum;
    }
    
    /**
     * 过去一周消费积分数是第三档的用户数（5k-10k）
     * 
     * @return 获取consumeLevel3WeekNum属性值
     */
    public Integer getConsumeLevel3WeekNum() {
        return this.consumeLevel3WeekNum;
    }
    
    /**
     * 过去一周消费积分数是第四档的用户数（10k以上）
     * 
     * @param consumeLevel4WeekNum 设置 consumeLevel4WeekNum 属性值为参数值 consumeLevel4WeekNum
     */
    public void setConsumeLevel4WeekNum(Integer consumeLevel4WeekNum) {
        this.consumeLevel4WeekNum = consumeLevel4WeekNum;
    }
    
    /**
     * 过去一周消费积分数是第四档的用户数（10k以上）
     * 
     * @return 获取consumeLevel4WeekNum属性值
     */
    public Integer getConsumeLevel4WeekNum() {
        return this.consumeLevel4WeekNum;
    }
    
    /**
     * 用户产生积分数收益型（总）
     * 
     * @param proceedsEarnings 设置 proceedsEarnings 属性值为参数值 proceedsEarnings
     */
    public void setProceedsEarnings(Integer proceedsEarnings) {
        this.proceedsEarnings = proceedsEarnings;
    }
    
    /**
     * 用户产生积分数收益型（总）
     * 
     * @return 获取proceedsEarnings属性值
     */
    public Integer getProceedsEarnings() {
        return this.proceedsEarnings;
    }
    
    /**
     * 用户产生积分数收益型（最大）
     * 
     * @param proceedsEarningsMax 设置 proceedsEarningsMax 属性值为参数值 proceedsEarningsMax
     */
    public void setProceedsEarningsMax(Integer proceedsEarningsMax) {
        this.proceedsEarningsMax = proceedsEarningsMax;
    }
    
    /**
     * 用户产生积分数收益型（最大）
     * 
     * @return 获取proceedsEarningsMax属性值
     */
    public Integer getProceedsEarningsMax() {
        return this.proceedsEarningsMax;
    }
    
    /**
     * 用户产生积分数收益型（最小）
     * 
     * @param proceedsEarningsMin 设置 proceedsEarningsMin 属性值为参数值 proceedsEarningsMin
     */
    public void setProceedsEarningsMin(Integer proceedsEarningsMin) {
        this.proceedsEarningsMin = proceedsEarningsMin;
    }
    
    /**
     * 用户产生积分数收益型（最小）
     * 
     * @return 获取proceedsEarningsMin属性值
     */
    public Integer getProceedsEarningsMin() {
        return this.proceedsEarningsMin;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第一档的用户数（1k以下）
     * 
     * @param proceedsLevel1WeekNum 设置 proceedsLevel1WeekNum 属性值为参数值 proceedsLevel1WeekNum
     */
    public void setProceedsLevel1WeekNum(Integer proceedsLevel1WeekNum) {
        this.proceedsLevel1WeekNum = proceedsLevel1WeekNum;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第一档的用户数（1k以下）
     * 
     * @return 获取proceedsLevel1WeekNum属性值
     */
    public Integer getProceedsLevel1WeekNum() {
        return this.proceedsLevel1WeekNum;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第二档的用户数（1k-5k）
     * 
     * @param proceedsLevel2WeekNum 设置 proceedsLevel2WeekNum 属性值为参数值 proceedsLevel2WeekNum
     */
    public void setProceedsLevel2WeekNum(Integer proceedsLevel2WeekNum) {
        this.proceedsLevel2WeekNum = proceedsLevel2WeekNum;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第二档的用户数（1k-5k）
     * 
     * @return 获取proceedsLevel2WeekNum属性值
     */
    public Integer getProceedsLevel2WeekNum() {
        return this.proceedsLevel2WeekNum;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第三档的用户数（5k-10k）
     * 
     * @param proceedsLevel3WeekNum 设置 proceedsLevel3WeekNum 属性值为参数值 proceedsLevel3WeekNum
     */
    public void setProceedsLevel3WeekNum(Integer proceedsLevel3WeekNum) {
        this.proceedsLevel3WeekNum = proceedsLevel3WeekNum;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第三档的用户数（5k-10k）
     * 
     * @return 获取proceedsLevel3WeekNum属性值
     */
    public Integer getProceedsLevel3WeekNum() {
        return this.proceedsLevel3WeekNum;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第四档的用户数（10k以上）
     * 
     * @param proceedsLevel4WeekNum 设置 proceedsLevel4WeekNum 属性值为参数值 proceedsLevel4WeekNum
     */
    public void setProceedsLevel4WeekNum(Integer proceedsLevel4WeekNum) {
        this.proceedsLevel4WeekNum = proceedsLevel4WeekNum;
    }
    
    /**
     * 过去一周产生积分数(收益型)是第四档的用户数（10k以上）
     * 
     * @return 获取proceedsLevel4WeekNum属性值
     */
    public Integer getProceedsLevel4WeekNum() {
        return this.proceedsLevel4WeekNum;
    }
    
    /**
     * 用户产生积分数推广型（总）
     * 
     * @param spreadEarnings 设置 spreadEarnings 属性值为参数值 spreadEarnings
     */
    public void setSpreadEarnings(Integer spreadEarnings) {
        this.spreadEarnings = spreadEarnings;
    }
    
    /**
     * 用户产生积分数推广型（总）
     * 
     * @return 获取spreadEarnings属性值
     */
    public Integer getSpreadEarnings() {
        return this.spreadEarnings;
    }
    
    /**
     * 用户产生积分数推广型（最大）
     * 
     * @param spreadEarningsMax 设置 spreadEarningsMax 属性值为参数值 spreadEarningsMax
     */
    public void setSpreadEarningsMax(Integer spreadEarningsMax) {
        this.spreadEarningsMax = spreadEarningsMax;
    }
    
    /**
     * 用户产生积分数推广型（最大）
     * 
     * @return 获取spreadEarningsMax属性值
     */
    public Integer getSpreadEarningsMax() {
        return this.spreadEarningsMax;
    }
    
    /**
     * 用户产生积分数推广型（最小）
     * 
     * @param spreadEarningsMin 设置 spreadEarningsMin 属性值为参数值 spreadEarningsMin
     */
    public void setSpreadEarningsMin(Integer spreadEarningsMin) {
        this.spreadEarningsMin = spreadEarningsMin;
    }
    
    /**
     * 用户产生积分数推广型（最小）
     * 
     * @return 获取spreadEarningsMin属性值
     */
    public Integer getSpreadEarningsMin() {
        return this.spreadEarningsMin;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第一档的用户数（1k以下）
     * 
     * @param spreadLevel1WeekNum 设置 spreadLevel1WeekNum 属性值为参数值 spreadLevel1WeekNum
     */
    public void setSpreadLevel1WeekNum(Integer spreadLevel1WeekNum) {
        this.spreadLevel1WeekNum = spreadLevel1WeekNum;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第一档的用户数（1k以下）
     * 
     * @return 获取spreadLevel1WeekNum属性值
     */
    public Integer getSpreadLevel1WeekNum() {
        return this.spreadLevel1WeekNum;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第二档的用户数（1k-5k）
     * 
     * @param spreadLevel2WeekNum 设置 spreadLevel2WeekNum 属性值为参数值 spreadLevel2WeekNum
     */
    public void setSpreadLevel2WeekNum(Integer spreadLevel2WeekNum) {
        this.spreadLevel2WeekNum = spreadLevel2WeekNum;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第二档的用户数（1k-5k）
     * 
     * @return 获取spreadLevel2WeekNum属性值
     */
    public Integer getSpreadLevel2WeekNum() {
        return this.spreadLevel2WeekNum;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第三档的用户数（5k-10k）
     * 
     * @param spreadLevel3WeekNum 设置 spreadLevel3WeekNum 属性值为参数值 spreadLevel3WeekNum
     */
    public void setSpreadLevel3WeekNum(Integer spreadLevel3WeekNum) {
        this.spreadLevel3WeekNum = spreadLevel3WeekNum;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第三档的用户数（5k-10k）
     * 
     * @return 获取spreadLevel3WeekNum属性值
     */
    public Integer getSpreadLevel3WeekNum() {
        return this.spreadLevel3WeekNum;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第四档的用户数（10k以上）
     * 
     * @param spreadLevel4WeekNum 设置 spreadLevel4WeekNum 属性值为参数值 spreadLevel4WeekNum
     */
    public void setSpreadLevel4WeekNum(Integer spreadLevel4WeekNum) {
        this.spreadLevel4WeekNum = spreadLevel4WeekNum;
    }
    
    /**
     * 过去一周产生积分数(推广型)是第四档的用户数（10k以上）
     * 
     * @return 获取spreadLevel4WeekNum属性值
     */
    public Integer getSpreadLevel4WeekNum() {
        return this.spreadLevel4WeekNum;
    }
    
    /**
     * 用户产生积分数消费型（总）
     * 
     * @param paymentEarnings 设置 paymentEarnings 属性值为参数值 paymentEarnings
     */
    public void setPaymentEarnings(Integer paymentEarnings) {
        this.paymentEarnings = paymentEarnings;
    }
    
    /**
     * 用户产生积分数消费型（总）
     * 
     * @return 获取paymentEarnings属性值
     */
    public Integer getPaymentEarnings() {
        return this.paymentEarnings;
    }
    
    /**
     * 用户产生积分数消费型（最大）
     * 
     * @param paymentEarningsMax 设置 paymentEarningsMax 属性值为参数值 paymentEarningsMax
     */
    public void setPaymentEarningsMax(Integer paymentEarningsMax) {
        this.paymentEarningsMax = paymentEarningsMax;
    }
    
    /**
     * 用户产生积分数消费型（最大）
     * 
     * @return 获取paymentEarningsMax属性值
     */
    public Integer getPaymentEarningsMax() {
        return this.paymentEarningsMax;
    }
    
    /**
     * 用户产生积分数消费型（最小）
     * 
     * @param paymentEarningsMin 设置 paymentEarningsMin 属性值为参数值 paymentEarningsMin
     */
    public void setPaymentEarningsMin(Integer paymentEarningsMin) {
        this.paymentEarningsMin = paymentEarningsMin;
    }
    
    /**
     * 用户产生积分数消费型（最小）
     * 
     * @return 获取paymentEarningsMin属性值
     */
    public Integer getPaymentEarningsMin() {
        return this.paymentEarningsMin;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第一档的用户数（1k以下）
     * 
     * @param paymentLevel1WeekNum 设置 paymentLevel1WeekNum 属性值为参数值 paymentLevel1WeekNum
     */
    public void setPaymentLevel1WeekNum(Integer paymentLevel1WeekNum) {
        this.paymentLevel1WeekNum = paymentLevel1WeekNum;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第一档的用户数（1k以下）
     * 
     * @return 获取paymentLevel1WeekNum属性值
     */
    public Integer getPaymentLevel1WeekNum() {
        return this.paymentLevel1WeekNum;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第二档的用户数（1k-5k）
     * 
     * @param paymentLevel2WeekNum 设置 paymentLevel2WeekNum 属性值为参数值 paymentLevel2WeekNum
     */
    public void setPaymentLevel2WeekNum(Integer paymentLevel2WeekNum) {
        this.paymentLevel2WeekNum = paymentLevel2WeekNum;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第二档的用户数（1k-5k）
     * 
     * @return 获取paymentLevel2WeekNum属性值
     */
    public Integer getPaymentLevel2WeekNum() {
        return this.paymentLevel2WeekNum;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第三档的用户数（5k-10k）
     * 
     * @param paymentLevel3WeekNum 设置 paymentLevel3WeekNum 属性值为参数值 paymentLevel3WeekNum
     */
    public void setPaymentLevel3WeekNum(Integer paymentLevel3WeekNum) {
        this.paymentLevel3WeekNum = paymentLevel3WeekNum;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第三档的用户数（5k-10k）
     * 
     * @return 获取paymentLevel3WeekNum属性值
     */
    public Integer getPaymentLevel3WeekNum() {
        return this.paymentLevel3WeekNum;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第四档的用户数（10k以上）
     * 
     * @param paymentLevel4WeekNum 设置 paymentLevel4WeekNum 属性值为参数值 paymentLevel4WeekNum
     */
    public void setPaymentLevel4WeekNum(Integer paymentLevel4WeekNum) {
        this.paymentLevel4WeekNum = paymentLevel4WeekNum;
    }
    
    /**
     * 过去一周产生积分数(消费型)是第四档的用户数（10k以上）
     * 
     * @return 获取paymentLevel4WeekNum属性值
     */
    public Integer getPaymentLevel4WeekNum() {
        return this.paymentLevel4WeekNum;
    }
    
    /**
     * 过去一周剩余积分是第一档的用户数（1k以下）
     * 
     * @param remainLevel1WeekNum 设置 remainLevel1WeekNum 属性值为参数值 remainLevel1WeekNum
     */
    public void setRemainLevel1WeekNum(Integer remainLevel1WeekNum) {
        this.remainLevel1WeekNum = remainLevel1WeekNum;
    }
    
    /**
     * 过去一周剩余积分是第一档的用户数（1k以下）
     * 
     * @return 获取remainLevel1WeekNum属性值
     */
    public Integer getRemainLevel1WeekNum() {
        return this.remainLevel1WeekNum;
    }
    
    /**
     * 过去一周剩余积分是第二档的用户数（1k-5k）
     * 
     * @param remainLevel2WeekNum 设置 remainLevel2WeekNum 属性值为参数值 remainLevel2WeekNum
     */
    public void setRemainLevel2WeekNum(Integer remainLevel2WeekNum) {
        this.remainLevel2WeekNum = remainLevel2WeekNum;
    }
    
    /**
     * 过去一周剩余积分是第二档的用户数（1k-5k）
     * 
     * @return 获取remainLevel2WeekNum属性值
     */
    public Integer getRemainLevel2WeekNum() {
        return this.remainLevel2WeekNum;
    }
    
    /**
     * 过去一周剩余积分)是第三档的用户数（5k-10k）
     * 
     * @param remainLevel3WeekNum 设置 remainLevel3WeekNum 属性值为参数值 remainLevel3WeekNum
     */
    public void setRemainLevel3WeekNum(Integer remainLevel3WeekNum) {
        this.remainLevel3WeekNum = remainLevel3WeekNum;
    }
    
    /**
     * 过去一周剩余积分)是第三档的用户数（5k-10k）
     * 
     * @return 获取remainLevel3WeekNum属性值
     */
    public Integer getRemainLevel3WeekNum() {
        return this.remainLevel3WeekNum;
    }
    
    /**
     * 过去一周剩余积分是第四档的用户数（10k以上）
     * 
     * @param remainLevel4WeekNum 设置 remainLevel4WeekNum 属性值为参数值 remainLevel4WeekNum
     */
    public void setRemainLevel4WeekNum(Integer remainLevel4WeekNum) {
        this.remainLevel4WeekNum = remainLevel4WeekNum;
    }
    
    /**
     * 过去一周剩余积分是第四档的用户数（10k以上）
     * 
     * @return 获取remainLevel4WeekNum属性值
     */
    public Integer getRemainLevel4WeekNum() {
        return this.remainLevel4WeekNum;
    }
    
    /**
     * 剩余积分
     * 
     * @param remainEarnings 设置 remainEarnings 属性值为参数值 remainEarnings
     */
    public void setRemainEarnings(Integer remainEarnings) {
        this.remainEarnings = remainEarnings;
    }
    
    /**
     * 剩余积分
     * 
     * @return 获取remainEarnings属性值
     */
    public Integer getRemainEarnings() {
        return this.remainEarnings;
    }
    
    /**
     * 剩余积分_最大值
     * 
     * @param remainEarningsMax 设置 remainEarningsMax 属性值为参数值 remainEarningsMax
     */
    public void setRemainEarningsMax(Integer remainEarningsMax) {
        this.remainEarningsMax = remainEarningsMax;
    }
    
    /**
     * 剩余积分_最大值
     * 
     * @return 获取remainEarningsMax属性值
     */
    public Integer getRemainEarningsMax() {
        return this.remainEarningsMax;
    }
    
    /**
     * 剩余积分_最小值
     * 
     * @param remainEarningsMin 设置 remainEarningsMin 属性值为参数值 remainEarningsMin
     */
    public void setRemainEarningsMin(Integer remainEarningsMin) {
        this.remainEarningsMin = remainEarningsMin;
    }
    
    /**
     * 剩余积分_最小值
     * 
     * @return 获取remainEarningsMin属性值
     */
    public Integer getRemainEarningsMin() {
        return this.remainEarningsMin;
    }
    
}
