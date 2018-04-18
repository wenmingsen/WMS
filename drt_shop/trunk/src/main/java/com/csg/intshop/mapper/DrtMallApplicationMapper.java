package com.csg.intshop.mapper;

import java.util.List;
import java.util.Map;

import com.csg.intshop.entity.DrtMallApplication;

/**
 * DrtMallApplicationMapper 积分商城应用接入表
 *
 * @author  罗金雄
 * @since   1.8
 * @version 2018年01月25日 罗金雄
 */
public interface DrtMallApplicationMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 */
	void insert(DrtMallApplication drtMallApplication);

	/**
	 * 批量保存
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 */
	void insertList(List<DrtMallApplication> drtMallApplicationList);

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 积分商城应用接入表 主键ID
	 */
	void delete(String id);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 */
	void update(DrtMallApplication drtMallApplication);

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 */
	void updateIfNotNull(DrtMallApplication drtMallApplication);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城应用接入表 主键ID
	 * @return 积分商城应用接入表 单条记录
	 */
	DrtMallApplication selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 * @return 积分商城应用接入表 记录集
	 */
	List<DrtMallApplication> selectList(DrtMallApplication drtMallApplication);
	
	/**
	 * 积分商城接口用
	 * 
	 * @param map 参数
	 * @return 积分商城应用接入表 记录集
	 */
	List<DrtMallApplication> selectListByinterface(Map<String,Object> map);

}