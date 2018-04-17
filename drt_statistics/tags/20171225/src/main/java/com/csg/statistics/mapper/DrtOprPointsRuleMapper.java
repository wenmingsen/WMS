package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtOprPointsRule;
import com.csg.statistics.entity.DrtOprPointsRuleBean;

/**
 * DrtOprPointsRuleMapper 积分规则
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
public interface DrtOprPointsRuleMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	void insert(DrtOprPointsRule drtOprPointsRule);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	void update(DrtOprPointsRule drtOprPointsRule);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param pointsRuleId 积分规则 主键ID
	 * @return 积分规则 单条记录
	 */
	DrtOprPointsRule selectByPrimaryKey(String pointsRuleId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtOprPointsRule 积分规则
	 * @return 积分规则 记录集
	 */
	List<DrtOprPointsRule> selectList(DrtOprPointsRule drtOprPointsRule);
	
	/**
	 * 获取单条记录
	 * 
	 * @param drtOprPointsRuleBean 积分规则自定义查询bean
	 */
	DrtOprPointsRule selectOneDrtOprPointsRule(DrtOprPointsRuleBean drtOprPointsRuleBean);

}