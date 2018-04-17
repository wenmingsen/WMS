package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtBankAccount;


/**
 * DrtBankAccountMapper 银行账户信息表（绑卡信息）
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
public interface DrtBankAccountMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtBankAccount 银行账户信息表（绑卡信息）
	 */
	void insert(DrtBankAccount drtBankAccount);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtBankAccount 银行账户信息表（绑卡信息）
	 */
	void update(DrtBankAccount drtBankAccount);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param bankAccountId 银行账户信息表（绑卡信息） 主键ID
	 * @return 银行账户信息表（绑卡信息） 单条记录
	 */
	DrtBankAccount selectByPrimaryKey(String bankAccountId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtBankAccount 银行账户信息表（绑卡信息）
	 * @return 银行账户信息表（绑卡信息） 记录集
	 */
	List<DrtBankAccount> selectList(DrtBankAccount drtBankAccount);
	
	/**
	 * 昨天用户ID分组的数据
	 * @return List<DrtBankAccount>
	 */
	List<DrtBankAccount> queryDrtBankAccount();

}