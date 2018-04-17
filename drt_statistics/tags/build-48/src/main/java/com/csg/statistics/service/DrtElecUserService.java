package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtElecUser;
import com.csg.statistics.mapper.DrtElecUserMapper;

/**
 * DrtElecUserService 用电户信息表（绑定信息）
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtElecUserService{
	
	/**用电户信息表（绑定信息）drtElecUserMapper接口*/
	@Autowired
	private DrtElecUserMapper drtElecUserMapper;

	/**
	 * 保存
	 * 
	 * @param drtElecUser 用电户信息表（绑定信息）
	 */
	public void insert(DrtElecUser drtElecUser) throws Exception{
		drtElecUserMapper.insert(drtElecUser);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtElecUser 用电户信息表（绑定信息）
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtElecUser drtElecUser) throws Exception{
		drtElecUserMapper.update(drtElecUser);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param elecUserId 用电户信息表（绑定信息） 主键ID
	 * @return 用电户信息表（绑定信息） 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtElecUser selectByPrimaryKey(String elecUserId) throws Exception{
		return drtElecUserMapper.selectByPrimaryKey(elecUserId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtElecUser 用电户信息表（绑定信息）
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtElecUser> selectList(DrtElecUser drtElecUser) throws Exception{
		return drtElecUserMapper.selectList(drtElecUser);
	}

	/**
	 * 昨天用户ID分组的数据
	 * 
	 */
	public List<DrtElecUser> queryDrtElecUser(){
		return drtElecUserMapper.queryDrtElecUser();
	}
}