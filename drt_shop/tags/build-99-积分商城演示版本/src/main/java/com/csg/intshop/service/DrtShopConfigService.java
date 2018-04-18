package com.csg.intshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopConfig;
import com.csg.intshop.mapper.DrtShopConfigMapper;

/**
 * DrtShopConfigService 积分商城配置表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2018年01月25日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtShopConfigService{
	
	/**积分商城配置表drtShopConfigMapper接口*/
	@Autowired
	private DrtShopConfigMapper drtShopConfigMapper;

	/**
	 * 保存
	 * 
	 * @param drtShopConfig 积分商城配置表
	 */
	public void insert(DrtShopConfig drtShopConfig) throws Exception{
		drtShopConfigMapper.insert(drtShopConfig);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopConfig 积分商城配置表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtShopConfig drtShopConfig) throws Exception{
		drtShopConfigMapper.update(drtShopConfig);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城配置表 主键ID
	 * @return 积分商城配置表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtShopConfig selectByPrimaryKey(String id) throws Exception{
		return drtShopConfigMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopConfig 积分商城配置表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtShopConfig> selectList(DrtShopConfig drtShopConfig) throws Exception{
		return drtShopConfigMapper.selectList(drtShopConfig);
	}
	
	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopConfig 积分商城配置表
	 * @throws Exception 出错抛出异常
	 */
	public void updateIfNotNull(DrtShopConfig drtShopConfig) throws Exception{
		drtShopConfigMapper.updateIfNotNull(drtShopConfig);
	}
	
	/**
	 * 获取积分商城配置项map
	 * @return drtShopConfigMap 积分商城配置项map
	 * @throws Exception 
	 */
	public Map<String, String> getDrtShopConfigMap() throws Exception{
		//查询条件封装
		DrtShopConfig drtShopConfigForQuery = new DrtShopConfig();
		drtShopConfigForQuery.setConfigStatus(1);
		drtShopConfigForQuery.setIsDelete(1);
		List<DrtShopConfig> drtShopConfigList = this.selectList(drtShopConfigForQuery);
		Map<String,String> drtShopConfigMap = new HashMap<String, String>();
		for(DrtShopConfig drtShopConfig:drtShopConfigList){
			drtShopConfigMap.put(drtShopConfig.getConfigKey(), drtShopConfig.getConfigValue());
		}
		return drtShopConfigMap;
	}

}