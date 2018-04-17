
package com.csg.statistics.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.statistics.entity.DrtMonitorPointsDay;
import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.service.DrtAccountService;
import com.csg.statistics.service.DrtOprPointsMonitoringService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;

/**
 * 每天积分类型种类及总数统计任务
 *
 * @author 惠新宇
 * @since 1.8
 * @version 2017年12月15日
 */
@Component
public class PointsMonitorJob {
    
    @Autowired
    private DrtOprPointsMonitoringService pointsMonitoringService;
    
    @Autowired
    private DrtAccountService drtAccountService;
    
    /**
     * 每天积分类型种类及总数统计任务
     * 
     */
    // @Scheduled(cron = "10 * * * * ?")
    public void pointsTypesTatistics() {
        System.err.println("----------------开始--------------------");
        // 获取今天开始时间
        Timestamp todayTime = new Timestamp(DateTimeUtils.getTodayStart().getTime());
        // 获取昨天开始时间
        Timestamp yesterdayTime = new Timestamp(DateTimeUtils.getYesterdayStart().getTime());
        // 获取一周前开始时间
        String weekDay = DateTimeUtils.getIntervalTime(DateTimeUtils.getTodayStart().getTime(), -8,
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Timestamp weekAgoTime = DateTimeUtils.converStrToTimestamp(weekDay);
        
        // 获取积分来源为收益的积分信息
        Map<String, Object> proceedsEarningsMap = pointsMonitoringService.queryEarnings(todayTime, yesterdayTime,
            weekAgoTime, SystemConstants.EARNINGS_TYPE_PROCEEDS);
        // 获取积分来源为推广的积分信息SystemConstants.EARNINGS_TYPE_SPREAD为推广
        Map<String, Object> spreadEarningsMap = pointsMonitoringService.queryEarnings(todayTime, yesterdayTime,
            weekAgoTime, SystemConstants.EARNINGS_TYPE_SPREAD);
        // 获取积分来源为消费的积分信息SystemConstants.EARNINGS_TYPE_CONSUME为推广
        Map<String, Object> paymentEarningsMap = pointsMonitoringService.queryEarnings(todayTime, yesterdayTime,
            weekAgoTime, SystemConstants.EARNINGS_TYPE_CONSUME);
        
        // 消费积分
        Map<String, Object> consumeEarningsMap = pointsMonitoringService.queryConsumeEarnings(todayTime, yesterdayTime,
            weekAgoTime);
        // 剩余积分的分布情况
        Map<String, Object> remainEarningsMap = pointsMonitoringService.queryRemainEarnings(todayTime);
        // 封装数据
        DrtMonitorPointsDay monitorPointsDay = packagemonitorEarning(proceedsEarningsMap, spreadEarningsMap,
            paymentEarningsMap, consumeEarningsMap, remainEarningsMap);
        // 获取当天日期
        String yesterday = DateTimeUtils.getIntervalTime(DateTimeUtils.getTodayStart().getTime(), -1,
            new SimpleDateFormat("yyyyMMdd"));
        monitorPointsDay.setPresentDate(Integer.valueOf(yesterday));
        monitorPointsDay.setId(UUIDUtil.generateUUID());
        int accountNum = drtAccountService.queryNum();
        monitorPointsDay.setPresentAccountNum(accountNum);
        // 插入到积分监控表中
        pointsMonitoringService.insertMonitorPoints(monitorPointsDay);
    }
    
    /**
     * 封装数据
     * 
     * @param proceedsEarningsMap 收益获取的积分信息
     * @param spreadEarningsMap 推广获取的积分信息
     * @param paymentEarningsMap 消费获取的积分信息
     * @param consumeEarningsMap 消费的积分信息
     * @param remainEarningsMap 剩余的积分信息
     * @return 积分监控的对象
     */
    private DrtMonitorPointsDay packagemonitorEarning(Map<String, Object> proceedsEarningsMap,
        Map<String, Object> spreadEarningsMap, Map<String, Object> paymentEarningsMap,
        Map<String, Object> consumeEarningsMap, Map<String, Object> remainEarningsMap) {
        DrtMonitorPointsDay monitorPoints = new DrtMonitorPointsDay();
        // 产生积分收益类型
        monitorPoints.setProceedsEarnings(Integer.valueOf(proceedsEarningsMap.get("earningsSum").toString()));
        monitorPoints.setProceedsEarningsMax(Integer.valueOf(proceedsEarningsMap.get("earningsMax").toString()));
        monitorPoints.setProceedsEarningsMin(Integer.valueOf(proceedsEarningsMap.get("earningsMin").toString()));
        monitorPoints.setProceedsLevel1WeekNum(Integer.valueOf(proceedsEarningsMap.get("level1WeekNum").toString()));
        monitorPoints.setProceedsLevel2WeekNum(Integer.valueOf(proceedsEarningsMap.get("level2WeekNum").toString()));
        monitorPoints.setProceedsLevel3WeekNum(Integer.valueOf(proceedsEarningsMap.get("level3WeekNum").toString()));
        monitorPoints.setProceedsLevel4WeekNum(Integer.valueOf(proceedsEarningsMap.get("level4WeekNum").toString()));
        // 产生积分推广类型
        monitorPoints.setSpreadEarnings(Integer.valueOf(spreadEarningsMap.get("earningsSum").toString()));
        monitorPoints.setSpreadEarningsMax(Integer.valueOf(spreadEarningsMap.get("earningsMax").toString()));
        monitorPoints.setSpreadEarningsMin(Integer.valueOf(spreadEarningsMap.get("earningsMin").toString()));
        monitorPoints.setSpreadLevel1WeekNum(Integer.valueOf(spreadEarningsMap.get("level1WeekNum").toString()));
        monitorPoints.setSpreadLevel2WeekNum(Integer.valueOf(spreadEarningsMap.get("level2WeekNum").toString()));
        monitorPoints.setSpreadLevel3WeekNum(Integer.valueOf(spreadEarningsMap.get("level3WeekNum").toString()));
        monitorPoints.setSpreadLevel4WeekNum(Integer.valueOf(spreadEarningsMap.get("level4WeekNum").toString()));
        // 产生积分消费类型
        monitorPoints.setPaymentEarnings(Integer.valueOf(paymentEarningsMap.get("earningsSum").toString()));
        monitorPoints.setPaymentEarningsMax(Integer.valueOf(paymentEarningsMap.get("earningsMax").toString()));
        monitorPoints.setPaymentEarningsMin(Integer.valueOf(paymentEarningsMap.get("earningsMin").toString()));
        monitorPoints.setPaymentLevel1WeekNum(Integer.valueOf(paymentEarningsMap.get("level1WeekNum").toString()));
        monitorPoints.setPaymentLevel2WeekNum(Integer.valueOf(paymentEarningsMap.get("level2WeekNum").toString()));
        monitorPoints.setPaymentLevel3WeekNum(Integer.valueOf(paymentEarningsMap.get("level3WeekNum").toString()));
        monitorPoints.setPaymentLevel4WeekNum(Integer.valueOf(paymentEarningsMap.get("level4WeekNum").toString()));
        
        // 消费积分
        monitorPoints.setConsumeEarnings(Integer.valueOf(consumeEarningsMap.get("earningsSum").toString()));
        monitorPoints.setConsumeEarningsMax(Integer.valueOf(consumeEarningsMap.get("earningsMax").toString()));
        monitorPoints.setConsumeEarningsMin(Integer.valueOf(consumeEarningsMap.get("earningsMin").toString()));
        monitorPoints.setConsumeLevel1WeekNum(Integer
            .valueOf(consumeEarningsMap.get("consumeLevel1WeekNum").toString()));
        monitorPoints.setConsumeLevel2WeekNum(Integer
            .valueOf(consumeEarningsMap.get("consumeLevel2WeekNum").toString()));
        monitorPoints.setConsumeLevel3WeekNum(Integer
            .valueOf(consumeEarningsMap.get("consumeLevel3WeekNum").toString()));
        monitorPoints.setConsumeLevel4WeekNum(Integer
            .valueOf(consumeEarningsMap.get("consumeLevel4WeekNum").toString()));
        // 剩余积分
        
        monitorPoints.setRemainEarnings(Integer.valueOf(remainEarningsMap.get("earningsSum").toString()));
        monitorPoints.setRemainEarningsMax(Integer.valueOf(remainEarningsMap.get("earningsMax").toString()));
        monitorPoints.setRemainEarningsMin(Integer.valueOf(remainEarningsMap.get("earningsMin").toString()));
        monitorPoints.setRemainLevel1WeekNum(Integer.valueOf(remainEarningsMap.get("remainLevel1WeekNum").toString()));
        monitorPoints.setRemainLevel2WeekNum(Integer.valueOf(remainEarningsMap.get("remainLevel2WeekNum").toString()));
        monitorPoints.setRemainLevel3WeekNum(Integer.valueOf(remainEarningsMap.get("remainLevel3WeekNum").toString()));
        monitorPoints.setRemainLevel4WeekNum(Integer.valueOf(remainEarningsMap.get("remainLevel4WeekNum").toString()));
        
        return monitorPoints;
    }
}
