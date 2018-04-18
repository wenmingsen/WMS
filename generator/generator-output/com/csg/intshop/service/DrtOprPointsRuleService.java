package com.csg.intshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtOprPointsRule;
import com.csg.intshop.mapper.DrtOprPointsRuleMapper;

/**
 * DrtOprPointsRuleService 积分规则
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年02月06日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtOprPointsRuleService{
	
	/**积分规则drtOprPointsRuleMapper接口*/
	@Autowired
	private DrtOprPointsRuleMapper drtOprPointsRuleMapper;

	/**
	 * 保存
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	public void insert(DrtOprPointsRule drtOprPointsRule) throws Exception{
		drtOprPointsRuleMapper.insert(drtOprPointsRule);
	}

	/**
	 * 批量保存
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	public void insertList(List<DrtOprPointsRule> drtOprPointsRuleList) throws Exception{
		drtOprPointsRuleMapper.insertList(drtOprPointsRuleList);
	}

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param pointsRuleId 积分规则 主键ID
	 * @throws Exception 出错抛出异常
	 */
	public void delete(String pointsRuleId) throws Exception{
		drtOprPointsRuleMapper.delete(pointsRuleId);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtOprPointsRule 积分规则
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtOprPointsRule drtOprPointsRule) throws Exception{
		drtOprPointsRuleMapper.update(drtOprPointsRule);
	}

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtOprPointsRule 积分规则
	 * @throws Exception 出错抛出异常
	 */
	public void updateIfNotNull(DrtOprPointsRule drtOprPointsRule) throws Exception{
		drtOprPointsRuleMapper.updateIfNotNull(drtOprPointsRule);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param pointsRuleId 积分规则 主键ID
	 * @return 积分规则 单条记录
	 */
	public DrtOprPointsRule selectByPrimaryKey(String pointsRuleId){
		return drtOprPointsRuleMapper.selectByPrimaryKey(pointsRuleId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtOprPointsRule 积分规则
	 */
	public List<DrtOprPointsRule> selectList(DrtOprPointsRule drtOprPointsRule){
		return drtOprPointsRuleMapper.selectList(drtOprPointsRule);
	}

}