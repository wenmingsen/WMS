package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtOprPointsRule;

/**
 * DrtOprPointsRuleMapper 积分规则
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年02月06日 曾令鹏
 */
public interface DrtOprPointsRuleMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	void insert(DrtOprPointsRule drtOprPointsRule);

	/**
	 * 批量保存
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	void insertList(List<DrtOprPointsRule> drtOprPointsRuleList);

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param pointsRuleId 积分规则 主键ID
	 */
	void delete(String pointsRuleId);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	void update(DrtOprPointsRule drtOprPointsRule);

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	void updateIfNotNull(DrtOprPointsRule drtOprPointsRule);
	
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

}