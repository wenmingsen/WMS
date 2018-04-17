
package com.csg.statistics.mapper;

import java.sql.Timestamp;
import java.util.Map;

import com.csg.statistics.entity.DrtMonitorPointsDay;

/**
 * 每天积分类型种类及总数统计任务
 * 
 * @author 惠新宇
 * @since 1.8
 * @version 2017年12月19日 惠新宇
 */

public interface DrtOprPointsMonitoringMapper {
    
    /**
     * 
     * 获取类型为收益的前一天的积分情况
     * 
     * @param map 条件
     * @return 积分信息
     */
    Map<String, Object> queryProceedsEarnings(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为收益的一周的积分总数为0-1k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryProceedsLevel1WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为收益的一周的积分总数为1k- 5k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryProceedsLevel2WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为收益的 一周的积分总数为5k- 10k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryProceedsLevel3WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为收益的一周的积分总数为10k以上
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryProceedsLevel4WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为推广或者消费的前一天的积分情况
     * 
     * @param map 条件
     * @return 积分信息
     */
    Map<String, Object> queryOtherEarnings(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为其他的一周的积分总数为0-1k（包括推广和消费产生的积分）
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryOtherLevel1WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为其他的一周的积分总数为1k- 5k（包括推广和消费产生的积分）
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryOtherLevel2WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为其他的一周的积分总数为5k- 10k（包括推广和消费产生的积分）
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryOtherLevel3WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取类型为其他的一周的积分总数为10k以上（包括推广和消费产生的积分）
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryOtherLevel4WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 消费积分的前一天情况
     * 
     * @param map 条件
     * @return 积分信息
     */
    Map<String, Object> queryConsumeEarnings(Map<String, Object> map);
    
    /**
     * 
     * 一周的消费积分总数为0-1k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryConsumeLevel1WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 一周的消费积分总数为1k- 5k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryConsumeLevel2WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 一周的消费积分总数为5k- 10k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryConsumeLevel3WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 一周的消费积分总数为10k以上
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryConsumeLevel4WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 当前时间剩余积分数在0-1k的人数
     * 
     * @param todayTime 时间
     * @return 积分信息
     */
    int queryRemainLevel1WeekNum(Timestamp todayTime);
    
    /**
     * 
     * 当前时间剩余的积分数为1k- 5k
     * 
     * @param todayTime 时间
     * @return 积分信息
     */
    int queryRemainLevel2WeekNum(Timestamp todayTime);
    
    /**
     * 
     * 当前时间剩余的积分数为5k- 10k
     * 
     * @param todayTime 时间
     * @return 积分信息
     */
    int queryRemainLevel3WeekNum(Timestamp todayTime);
    
    /**
     * 
     * 当前时间剩余的积分数为10k以上
     * 
     * @param todayTime 时间
     * @return 积分信息
     */
    int queryRemainLevel4WeekNum(Timestamp todayTime);
    
    /**
     * 
     * 插入积分监控信息
     * 
     * @param monitorPointsDay 积分监控对象
     * @return 积分信息
     */
    void insertMonitorPoints(DrtMonitorPointsDay monitorPointsDay);
}
