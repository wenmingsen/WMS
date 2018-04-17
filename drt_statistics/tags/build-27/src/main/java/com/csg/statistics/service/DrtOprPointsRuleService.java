package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtOprPointsRule;
import com.csg.statistics.entity.DrtOprPointsRuleBean;
import com.csg.statistics.mapper.DrtOprPointsRuleMapper;

/**
 * DrtOprPointsRuleService 积分规则
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月08日 曾令鹏
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
	 * 通过主键获取单条记录
	 * 
	 * @param pointsRuleId 积分规则 主键ID
	 * @return 积分规则 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtOprPointsRule selectByPrimaryKey(String pointsRuleId) throws Exception{
		return drtOprPointsRuleMapper.selectByPrimaryKey(pointsRuleId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtOprPointsRule 积分规则
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtOprPointsRule> selectList(DrtOprPointsRule drtOprPointsRule) throws Exception{
		return drtOprPointsRuleMapper.selectList(drtOprPointsRule);
	}
	
	/**
	 * 获取单条记录
	 * 
	 * @param drtOprPointsRuleBean 积分规则自定义查询bean
	 * @throws Exception 出错抛出异常
	 */
	public DrtOprPointsRule selectOneDrtOprPointsRule(DrtOprPointsRuleBean drtOprPointsRuleBean) throws Exception{
		return drtOprPointsRuleMapper.selectOneDrtOprPointsRule(drtOprPointsRuleBean);
	}

}