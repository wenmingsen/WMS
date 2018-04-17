
package com.csg.statistics.mapper;

import java.sql.Timestamp;
import java.util.List;
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
     * 保存
     * 
     * @param drtMonitorPointsDay 日积分监控表
     */
    void insert(DrtMonitorPointsDay drtMonitorPointsDay);
    
    /**
     * 更新
     * 通过主键更新记录
     * 
     * @param drtMonitorPointsDay 日积分监控表
     */
    void update(DrtMonitorPointsDay drtMonitorPointsDay);
    
    /**
     * 通过主键获取单条记录
     * 
     * @param id 日积分监控表 主键ID
     * @return 日积分监控表 单条记录
     */
    DrtMonitorPointsDay selectByPrimaryKey(String id);
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtMonitorPointsDay 日积分监控表
     * @return 日积分监控表 记录集
     */
    List<DrtMonitorPointsDay> selectList(DrtMonitorPointsDay drtMonitorPointsDay);
    
    /**
     * 
     * 获取积分情况
     * 
     * @param map 条件
     * @return 积分信息
     */
    Map<String, Object> queryEarnings(Map<String, Object> map);
    
    /**
     * 
     * 获取一周的积分总数为0-1k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryLevel1WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取一周的积分总数为1k- 5k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryLevel2WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取一周的积分总数为5k- 10k
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryLevel3WeekNum(Map<String, Object> map);
    
    /**
     * 
     * 获取一周的积分总数为10k以上
     * 
     * @param map 查询条件map
     * @return 积分信息
     */
    int queryLevel4WeekNum(Map<String, Object> map);
    
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
     * 查询当前剩余积分的最大值最小值
     * 
     * @return 积分信息
     */
    Map<String, Object> queryRemainEarnings();
    
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
}
