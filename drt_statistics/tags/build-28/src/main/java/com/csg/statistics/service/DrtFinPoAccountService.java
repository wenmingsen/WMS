package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.mapper.DrtFinPoAccountMapper;
import com.github.pagehelper.PageHelper;

/**
 * DrtFinPoAccountService 理财账户信息表（预购）
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月08日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinPoAccountService{
	
	/**理财账户信息表（预购）drtFinPoAccountMapper接口*/
	@Autowired
	private DrtFinPoAccountMapper drtFinPoAccountMapper;

	/**
	 * 保存
	 * 
	 * @param drtFinPoAccount 理财账户信息表（预购）
	 */
	public void insert(DrtFinPoAccount drtFinPoAccount) throws Exception{
		drtFinPoAccountMapper.insert(drtFinPoAccount);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoAccount 理财账户信息表（预购）
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinPoAccount drtFinPoAccount) throws Exception{
		drtFinPoAccountMapper.update(drtFinPoAccount);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poAccountId 理财账户信息表（预购） 主键ID
	 * @return 理财账户信息表（预购） 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtFinPoAccount selectByPrimaryKey(String poAccountId) throws Exception{
		return drtFinPoAccountMapper.selectByPrimaryKey(poAccountId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoAccount 理财账户信息表（预购）
	 */
	public List<DrtFinPoAccount> selectList(DrtFinPoAccount drtFinPoAccount){
		return drtFinPoAccountMapper.selectList(drtFinPoAccount);
	}
	
	/**
	 * 通过自定义非空字段获取记录集（带分页）
	 * 
	 * @param drtFinPoAccount 理财账户信息表（预购）
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinPoAccount> selectListByPage(DrtFinPoAccount drtFinPoAccount, int pageNum, int pageSize) throws Exception{
		PageHelper.startPage(pageNum, pageSize);
		return drtFinPoAccountMapper.selectList(drtFinPoAccount);
	}
	
	/**
	 * 昨天用户ID分组的数据 
	 * 
	 * @return 理财账户信息表（预购） 记录集
	 */
	public List<DrtFinPoAccount> queryDrtFinPoAccount(){
		return drtFinPoAccountMapper.queryDrtFinPoAccount();
	}
	
	
}