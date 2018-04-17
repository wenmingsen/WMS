package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtBankAccount;
import com.csg.statistics.mapper.DrtBankAccountMapper;


/**
 * DrtBankAccountService 银行账户信息表（绑卡信息）
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtBankAccountService{
	
	/**银行账户信息表（绑卡信息）drtBankAccountMapper接口*/
	@Autowired
	private DrtBankAccountMapper drtBankAccountMapper;

	/**
	 * 保存
	 * 
	 * @param drtBankAccount 银行账户信息表（绑卡信息）
	 */
	public void insert(DrtBankAccount drtBankAccount) throws Exception{
		drtBankAccountMapper.insert(drtBankAccount);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtBankAccount 银行账户信息表（绑卡信息）
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtBankAccount drtBankAccount) throws Exception{
		drtBankAccountMapper.update(drtBankAccount);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param bankAccountId 银行账户信息表（绑卡信息） 主键ID
	 * @return 银行账户信息表（绑卡信息） 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtBankAccount selectByPrimaryKey(String bankAccountId) throws Exception{
		return drtBankAccountMapper.selectByPrimaryKey(bankAccountId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtBankAccount 银行账户信息表（绑卡信息）
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtBankAccount> selectList(DrtBankAccount drtBankAccount) throws Exception{
		return drtBankAccountMapper.selectList(drtBankAccount);
	}
	
	/**
	 * 昨天用户ID分组的数据
	 * @return List<DrtBankAccount>
	 */
	public List<DrtBankAccount> queryDrtBankAccount(){
		return drtBankAccountMapper.queryDrtBankAccount();
	}

}