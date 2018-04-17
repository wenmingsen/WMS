
package com.csg.statistics.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.entity.DrtMonitorPointsDay;
import com.csg.statistics.mapper.DrtOprPointsMonitoringMapper;

/**
 * 每天积分类型种类及总数统计任务
 *
 * @author 惠新宇
 * @since 1.8
 * @version 2017年12月15日
 */
@Service
public class DrtOprPointsMonitoringService {
    
    @Autowired
    DrtOprPointsMonitoringMapper pointsMonitoringMapper;
    
    /**
     * 
     * 获取收益产生的积分情况
     * 
     * @param todayTime 当天的开始时间
     * @param yesterdayTime 前一天的开始时间
     * @param weekAgoTime 一周前的时间
     * @return 积分信息
     */
    public Map<String, Object> queryProceedsEarnings(Timestamp todayTime, Timestamp yesterdayTime, Timestamp weekAgoTime) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("todayTime", todayTime);
        paramMap.put("yesterdayTime", yesterdayTime);
        paramMap.put("weekAgoTime", weekAgoTime);
        Map<String, Object> recordEarningMap = pointsMonitoringMapper.queryProceedsEarnings(paramMap);
        if (recordEarningMap == null) {
            recordEarningMap = new HashMap<String, Object>();
            recordEarningMap.put("earningsMin", 0);
            recordEarningMap.put("earningsMax", 0);
            recordEarningMap.put("earningsSum", 0);
        }
        int proceedsLevel1WeekNum = pointsMonitoringMapper.queryProceedsLevel1WeekNum(paramMap);
        recordEarningMap.put("proceedsLevel1WeekNum", proceedsLevel1WeekNum);
        int proceedsLevel2WeekNum = pointsMonitoringMapper.queryProceedsLevel2WeekNum(paramMap);
        recordEarningMap.put("proceedsLevel2WeekNum", proceedsLevel2WeekNum);
        int proceedsLevel3WeekNum = pointsMonitoringMapper.queryProceedsLevel3WeekNum(paramMap);
        recordEarningMap.put("proceedsLevel3WeekNum", proceedsLevel3WeekNum);
        int proceedsLevel4WeekNum = pointsMonitoringMapper.queryProceedsLevel4WeekNum(paramMap);
        recordEarningMap.put("proceedsLevel4WeekNum", proceedsLevel4WeekNum);
        return recordEarningMap;
    }
    
    /**
     * 
     * 获取其他产生的积分情况（推广和消费）
     * 
     * @param todayTime 当天的开始时间
     * @param yesterdayTime 前一天的开始时间
     * @param weekAgoTime 一周前的时间
     * @param type 获取的积分来源为推广
     * @return 积分信息
     */
    public Map<String, Object> queryOtherEarnings(Timestamp todayTime, Timestamp yesterdayTime, Timestamp weekAgoTime,
        String type) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("todayTime", todayTime);
        paramMap.put("yesterdayTime", yesterdayTime);
        paramMap.put("weekAgoTime", weekAgoTime);
        paramMap.put("type", type);
        Map<String, Object> otherEarningMap = pointsMonitoringMapper.queryOtherEarnings(paramMap);
        if (otherEarningMap == null) {
            otherEarningMap = new HashMap<String, Object>();
            otherEarningMap.put("earningsMin", 0);
            otherEarningMap.put("earningsMax", 0);
            otherEarningMap.put("earningsSum", 0);
        }
        int otherLevel1WeekNum = pointsMonitoringMapper.queryOtherLevel1WeekNum(paramMap);
        otherEarningMap.put("otherLevel1WeekNum", otherLevel1WeekNum);
        int otherLevel2WeekNum = pointsMonitoringMapper.queryOtherLevel2WeekNum(paramMap);
        otherEarningMap.put("otherLevel2WeekNum", otherLevel2WeekNum);
        int otherLevel3WeekNum = pointsMonitoringMapper.queryOtherLevel3WeekNum(paramMap);
        otherEarningMap.put("otherLevel3WeekNum", otherLevel3WeekNum);
        int otherLevel4WeekNum = pointsMonitoringMapper.queryOtherLevel4WeekNum(paramMap);
        otherEarningMap.put("otherLevel4WeekNum", otherLevel4WeekNum);
        
        return otherEarningMap;
    }
    
    /**
     * 
     * 消费积分情况
     * 
     * @param todayTime 当天的开始时间
     * @param yesterdayTime 前一天的开始时间
     * @param weekAgoTime 一周前的时间
     * @return 消费积分信息
     */
    public Map<String, Object> queryConsumeEarnings(Timestamp todayTime, Timestamp yesterdayTime, Timestamp weekAgoTime) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("todayTime", todayTime);
        paramMap.put("yesterdayTime", yesterdayTime);
        paramMap.put("weekAgoTime", weekAgoTime);
        Map<String, Object> consumeEarningMap = pointsMonitoringMapper.queryConsumeEarnings(paramMap);
        if (consumeEarningMap == null) {
            consumeEarningMap = new HashMap<String, Object>();
            consumeEarningMap.put("earningsMin", 0);
            consumeEarningMap.put("earningsMax", 0);
            consumeEarningMap.put("earningsSum", 0);
        }
        int consumeLevel1WeekNum = pointsMonitoringMapper.queryConsumeLevel1WeekNum(paramMap);
        consumeEarningMap.put("consumeLevel1WeekNum", consumeLevel1WeekNum);
        int consumeLevel2WeekNum = pointsMonitoringMapper.queryConsumeLevel2WeekNum(paramMap);
        consumeEarningMap.put("consumeLevel2WeekNum", consumeLevel2WeekNum);
        int consumeLevel3WeekNum = pointsMonitoringMapper.queryConsumeLevel3WeekNum(paramMap);
        consumeEarningMap.put("consumeLevel3WeekNum", consumeLevel3WeekNum);
        int consumeLevel4WeekNum = pointsMonitoringMapper.queryConsumeLevel4WeekNum(paramMap);
        consumeEarningMap.put("consumeLevel4WeekNum", consumeLevel4WeekNum);
        
        return consumeEarningMap;
    }
    
    /**
     * 消费积分情况
     * 
     * @param todayTime 当天的开始时间
     * @return 消费积分信息
     */
    public Map<String, Object> queryRemainEarnings(Timestamp todayTime) {
        Map<String, Object> remainMap = new HashMap<String, Object>();
        int remainLevel1WeekNum = pointsMonitoringMapper.queryRemainLevel1WeekNum(todayTime);
        remainMap.put("remainLevel1WeekNum", remainLevel1WeekNum);
        int remainLevel2WeekNum = pointsMonitoringMapper.queryRemainLevel2WeekNum(todayTime);
        remainMap.put("remainLevel2WeekNum", remainLevel2WeekNum);
        int remainLevel3WeekNum = pointsMonitoringMapper.queryRemainLevel3WeekNum(todayTime);
        remainMap.put("remainLevel3WeekNum", remainLevel3WeekNum);
        int remainLevel4WeekNum = pointsMonitoringMapper.queryRemainLevel4WeekNum(todayTime);
        remainMap.put("remainLevel4WeekNum", remainLevel4WeekNum);
        return remainMap;
    }
    
    public void insertMonitorPoints(DrtMonitorPointsDay monitorPointsDay) {
        pointsMonitoringMapper.insertMonitorPoints(monitorPointsDay);
    }
}
