package com.csg.intshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtMallApplication;
import com.csg.intshop.mapper.DrtMallApplicationMapper;

/**
 * DrtMallApplicationService 积分商城应用接入表
 *
 * @author  罗金雄
 * @since   1.8
 * @version 2018年01月25日 罗金雄
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMallApplicationService{
	
	/**积分商城应用接入表drtMallApplicationMapper接口*/
	@Autowired
	private DrtMallApplicationMapper drtMallApplicationMapper;

	/**
	 * 保存
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 */
	public void insert(DrtMallApplication drtMallApplication) throws Exception{
		drtMallApplicationMapper.insert(drtMallApplication);
	}

	/**
	 * 批量保存
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 */
	public void insertList(List<DrtMallApplication> drtMallApplicationList) throws Exception{
		drtMallApplicationMapper.insertList(drtMallApplicationList);
	}

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 积分商城应用接入表 主键ID
	 * @throws Exception 出错抛出异常
	 */
	public void delete(String id) throws Exception{
		drtMallApplicationMapper.delete(id);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMallApplication drtMallApplication) throws Exception{
		drtMallApplicationMapper.update(drtMallApplication);
	}

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 * @throws Exception 出错抛出异常
	 */
	public void updateIfNotNull(DrtMallApplication drtMallApplication) throws Exception{
		drtMallApplicationMapper.updateIfNotNull(drtMallApplication);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城应用接入表 主键ID
	 * @return 积分商城应用接入表 单条记录
	 */
	public DrtMallApplication selectByPrimaryKey(String id){
		return drtMallApplicationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallApplication 积分商城应用接入表
	 */
	public List<DrtMallApplication> selectList(DrtMallApplication drtMallApplication){
		return drtMallApplicationMapper.selectList(drtMallApplication);
	}

	/**
	 * 积分商城接口用
	 * 
	 * @param map 参数
	 * @return 积分商城应用接入表 记录集
	 */
	public List<DrtMallApplication> selectListByinterface(Map<String,Object> map){
		return drtMallApplicationMapper.selectListByinterface(map);
	}
}