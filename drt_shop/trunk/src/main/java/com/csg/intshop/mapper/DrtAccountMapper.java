package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtAccountVO;

/**
 * DrtAccountMapper 登录账户信息表（主要包括电融通账户、密码）
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年02月08日 曾令鹏
 */
public interface DrtAccountMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
	 */
	void insert(DrtAccountVO drtAccount);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
	 */
	void update(DrtAccountVO drtAccount);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param accountId 登录账户信息表（主要包括电融通账户、密码） 主键ID
	 * @return 登录账户信息表（主要包括电融通账户、密码） 单条记录
	 */
	DrtAccountVO selectByPrimaryKey(String accountId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtAccount 登录账户信息表（主要包括电融通账户、密码）
	 * @return 登录账户信息表（主要包括电融通账户、密码） 记录集
	 */
	List<DrtAccountVO> selectList(DrtAccountVO drtAccount);

}