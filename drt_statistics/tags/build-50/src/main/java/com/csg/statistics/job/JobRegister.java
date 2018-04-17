
package com.csg.statistics.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 任务注册
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2017年12月27日 曾令鹏
 */
@Component
public class JobRegister {
    
    /** 日志 */
    private Logger log = LoggerFactory.getLogger(JobRegister.class);
    
    /** 访客地域分布 计算任务 */
    @Autowired
    private VisitorRegionJob visitorRegionJob;
    
    /** 用户留存率 计算任务 */
    @Autowired
    private UserRetentionRecordComputingJob userRetentionRecordComputingJob;
    
    /** 积分收益计算任务 */
    @Autowired
    private PointsEarningsComputingJob pointsEarningsComputingJob;
    
    /** 用户监控任务 */
    @Autowired
    private UserMonitorComputingJob userMonitorComputingJob;
    
    /** 每天积分类型种类及总数统计任务 */
    @Autowired
    private PointsMonitorJob pointsMonitorJob;
    
    /** 资金监控统计任务 */
    @Autowired
    private FundComputingJob fundComputingJob;
    
    @Autowired
    private FinanceJob financeJob;
    
    @Autowired
    private OneDayJob oneDayJob;
    
    /**
     * 资产代办业务（01-交易）：每5分钟执行一次
     * 
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void startFinanceJob() {
        financeJob.finUnfinishedWorkJob();
    }
    
    /**
     * 业务用户统计（新增用户数量，实名认证用户数量，绑定银行卡用户数量，活跃度用户数量）
     * 
     * 00:15
     * 
     */
    @Scheduled(cron = "0 15 0 * * ?")
    public void startOneDayJob() {
        oneDayJob.statisticsBusinessUser();
    }
    
    /**
     * 用户监控登陆次数统计 00:30
     *
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void startUserMonitorComputingJob() {
        userMonitorComputingJob.userMonitorLoginComputing();
        
        // 积分收益计算 01:00
        pointsEarningsComputingJob.pointsEarningsComputing();
        
        // 留存率计算：01:30
        userRetentionRecordComputingJob.start();
        
        // 访客地域计算：02:00
        visitorRegionJob.start();
        
    }
    
    /**
     * 资金计算：02:30
     * 
     */
    @Scheduled(cron = "0 30 2 * * ?")
    public void startFundComputingJob() {
        try {
            fundComputingJob.fundComputing();
        } catch (Exception e) {
            log.error("资金监控统计任务 - {}" + e.getMessage());
        }
    }
    
    /**
     * 每天积分类型种类及总数统计任务：03:00
     * 
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void start() {
        try {
            pointsMonitorJob.pointsTypesTatistics();
        } catch (Exception e) {
            log.error("积分类型种类及总数统计 - {}" + e.getMessage());
        }
    }
    
}
