package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtElecUser;

/**
 * DrtElecUserMapper 用电户信息表（绑定信息）
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
public interface DrtElecUserMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtElecUser 用电户信息表（绑定信息）
	 */
	void insert(DrtElecUser drtElecUser);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtElecUser 用电户信息表（绑定信息）
	 */
	void update(DrtElecUser drtElecUser);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param elecUserId 用电户信息表（绑定信息） 主键ID
	 * @return 用电户信息表（绑定信息） 单条记录
	 */
	DrtElecUser selectByPrimaryKey(String elecUserId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtElecUser 用电户信息表（绑定信息）
	 * @return 用电户信息表（绑定信息） 记录集
	 */
	List<DrtElecUser> selectList(DrtElecUser drtElecUser);
	
	/**
	 * 昨天用户ID分组的数据
	 * 
	 */
	List<DrtElecUser> queryDrtElecUser();

}