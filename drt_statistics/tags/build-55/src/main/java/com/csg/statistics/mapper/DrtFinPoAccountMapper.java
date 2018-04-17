package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtFinPoAccount;

/**
 * DrtFinPoAccountMapper 理财账户信息表（预购）
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月08日 曾令鹏
 */
public interface DrtFinPoAccountMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtFinPoAccount 理财账户信息表（预购）
	 */
	void insert(DrtFinPoAccount drtFinPoAccount);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoAccount 理财账户信息表（预购）
	 */
	void update(DrtFinPoAccount drtFinPoAccount);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poAccountId 理财账户信息表（预购） 主键ID
	 * @return 理财账户信息表（预购） 单条记录
	 */
	DrtFinPoAccount selectByPrimaryKey(String poAccountId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoAccount 理财账户信息表（预购）
	 * @return 理财账户信息表（预购） 记录集
	 */
	List<DrtFinPoAccount> selectList(DrtFinPoAccount drtFinPoAccount);

	/**
	 * 昨天用户ID分组的数据 
	 * 
	 * @return 理财账户信息表（预购） 记录集
	 */
	List<DrtFinPoAccount> queryDrtFinPoAccount();
}