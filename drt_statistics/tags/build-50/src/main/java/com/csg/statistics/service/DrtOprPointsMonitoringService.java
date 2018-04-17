
package com.csg.statistics.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
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
    DrtOprPointsMonitoringMapper drtMonitorPointsDayMapper;
    
    /**
     * 保存
     * 
     * @param drtMonitorPointsDay 日积分监控表
     */
    public void insert(DrtMonitorPointsDay drtMonitorPointsDay) throws Exception {
        drtMonitorPointsDayMapper.insert(drtMonitorPointsDay);
    }
    
    /**
     * 更新
     * <p>
     * 通过主键更新记录
     * </p>
     * 
     * @param drtMonitorPointsDay 日积分监控表
     * @throws Exception 出错抛出异常
     */
    public void update(DrtMonitorPointsDay drtMonitorPointsDay) throws Exception {
        drtMonitorPointsDayMapper.update(drtMonitorPointsDay);
    }
    
    /**
     * 通过主键获取单条记录
     * 
     * @param id 日积分监控表 主键ID
     * @return 日积分监控表 单条记录
     * @throws Exception 出错抛出异常
     */
    public DrtMonitorPointsDay selectByPrimaryKey(String id) throws Exception {
        return drtMonitorPointsDayMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtMonitorPointsDay 日积分监控表
     * @throws Exception 出错抛出异常
     */
    public List<DrtMonitorPointsDay> selectList(DrtMonitorPointsDay drtMonitorPointsDay) {
        return drtMonitorPointsDayMapper.selectList(drtMonitorPointsDay);
    }
    
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
        Map<String, Object> earningMap = drtMonitorPointsDayMapper.queryEarnings(paramMap);
        if (earningMap == null) {
            earningMap = new HashMap<String, Object>();
            earningMap.put("earningsMin", 0);
            earningMap.put("earningsMax", 0);
            earningMap.put("earningsSum", 0);
        }
        int level1WeekNum = drtMonitorPointsDayMapper.queryLevel1WeekNum(paramMap);
        earningMap.put("level1WeekNum", level1WeekNum);
        int level2WeekNum = drtMonitorPointsDayMapper.queryLevel2WeekNum(paramMap);
        earningMap.put("level2WeekNum", level2WeekNum);
        int level3WeekNum = drtMonitorPointsDayMapper.queryLevel3WeekNum(paramMap);
        earningMap.put("level3WeekNum", level3WeekNum);
        int level4WeekNum = drtMonitorPointsDayMapper.queryLevel4WeekNum(paramMap);
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
        Map<String, Object> consumeEarningMap = drtMonitorPointsDayMapper.queryConsumeEarnings(paramMap);
        if (consumeEarningMap == null) {
            consumeEarningMap = new HashMap<String, Object>();
            consumeEarningMap.put("earningsMin", 0);
            consumeEarningMap.put("earningsMax", 0);
            consumeEarningMap.put("earningsSum", 0);
        }
        int consumeLevel1WeekNum = drtMonitorPointsDayMapper.queryConsumeLevel1WeekNum(paramMap);
        consumeEarningMap.put("consumeLevel1WeekNum", consumeLevel1WeekNum);
        int consumeLevel2WeekNum = drtMonitorPointsDayMapper.queryConsumeLevel2WeekNum(paramMap);
        consumeEarningMap.put("consumeLevel2WeekNum", consumeLevel2WeekNum);
        int consumeLevel3WeekNum = drtMonitorPointsDayMapper.queryConsumeLevel3WeekNum(paramMap);
        consumeEarningMap.put("consumeLevel3WeekNum", consumeLevel3WeekNum);
        int consumeLevel4WeekNum = drtMonitorPointsDayMapper.queryConsumeLevel4WeekNum(paramMap);
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
        Map<String, Object> remainEarningMap = drtMonitorPointsDayMapper.queryRemainEarnings();
        if (remainEarningMap == null) {
            remainEarningMap = new HashMap<String, Object>();
            remainEarningMap.put("earningsMin", 0);
            remainEarningMap.put("earningsMax", 0);
            remainEarningMap.put("earningsSum", 0);
        }
        int remainLevel1WeekNum = drtMonitorPointsDayMapper.queryRemainLevel1WeekNum(todayTime);
        remainEarningMap.put("remainLevel1WeekNum", remainLevel1WeekNum);
        int remainLevel2WeekNum = drtMonitorPointsDayMapper.queryRemainLevel2WeekNum(todayTime);
        remainEarningMap.put("remainLevel2WeekNum", remainLevel2WeekNum);
        int remainLevel3WeekNum = drtMonitorPointsDayMapper.queryRemainLevel3WeekNum(todayTime);
        remainEarningMap.put("remainLevel3WeekNum", remainLevel3WeekNum);
        int remainLevel4WeekNum = drtMonitorPointsDayMapper.queryRemainLevel4WeekNum(todayTime);
        remainEarningMap.put("remainLevel4WeekNum", remainLevel4WeekNum);
        return remainEarningMap;
    }
}
