
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
     * 获取积分情况
     * 
     * @param todayTime 当天的开始时间
     * @param yesterdayTime 前一天的开始时间
     * @param weekAgoTime 一周前的时间
     * @param type 获取的积分来源类型
     * @return 积分信息
     */
    public Map<String, Object> queryEarnings(Timestamp todayTime, Timestamp yesterdayTime, Timestamp weekAgoTime,
        String type) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("todayTime", todayTime);
        paramMap.put("yesterdayTime", yesterdayTime);
        paramMap.put("weekAgoTime", weekAgoTime);
        paramMap.put("type", type);
        Map<String, Object> earningMap = pointsMonitoringMapper.queryEarnings(paramMap);
        if (earningMap == null) {
            earningMap = new HashMap<String, Object>();
            earningMap.put("earningsMin", 0);
            earningMap.put("earningsMax", 0);
            earningMap.put("earningsSum", 0);
        }
        int level1WeekNum = pointsMonitoringMapper.queryLevel1WeekNum(paramMap);
        earningMap.put("level1WeekNum", level1WeekNum);
        int level2WeekNum = pointsMonitoringMapper.queryLevel2WeekNum(paramMap);
        earningMap.put("level2WeekNum", level2WeekNum);
        int level3WeekNum = pointsMonitoringMapper.queryLevel3WeekNum(paramMap);
        earningMap.put("level3WeekNum", level3WeekNum);
        int level4WeekNum = pointsMonitoringMapper.queryLevel4WeekNum(paramMap);
        earningMap.put("level4WeekNum", level4WeekNum);
        return earningMap;
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
     * 剩余积分情况
     * 
     * @param todayTime 当天的开始时间
     * @return 消费积分信息
     */
    public Map<String, Object> queryRemainEarnings(Timestamp todayTime) {
        Map<String, Object> remainEarningMap = pointsMonitoringMapper.queryRemainEarnings();
        if (remainEarningMap == null) {
            remainEarningMap = new HashMap<String, Object>();
            remainEarningMap.put("earningsMin", 0);
            remainEarningMap.put("earningsMax", 0);
            remainEarningMap.put("earningsSum", 0);
        }
        int remainLevel1WeekNum = pointsMonitoringMapper.queryRemainLevel1WeekNum(todayTime);
        remainEarningMap.put("remainLevel1WeekNum", remainLevel1WeekNum);
        int remainLevel2WeekNum = pointsMonitoringMapper.queryRemainLevel2WeekNum(todayTime);
        remainEarningMap.put("remainLevel2WeekNum", remainLevel2WeekNum);
        int remainLevel3WeekNum = pointsMonitoringMapper.queryRemainLevel3WeekNum(todayTime);
        remainEarningMap.put("remainLevel3WeekNum", remainLevel3WeekNum);
        int remainLevel4WeekNum = pointsMonitoringMapper.queryRemainLevel4WeekNum(todayTime);
        remainEarningMap.put("remainLevel4WeekNum", remainLevel4WeekNum);
        return remainEarningMap;
    }
    
    public void insertMonitorPoints(DrtMonitorPointsDay monitorPointsDay) {
        pointsMonitoringMapper.insertMonitorPoints(monitorPointsDay);
    }
}
