package com.csg.intshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.mapper.DrtMallOrderDetailMapper;

/**
 * DrtMallOrderDetailService 积分商城订单明细表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月23日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMallOrderDetailService{
	
	/**积分商城订单明细表drtMallOrderDetailMapper接口*/
	@Autowired
	private DrtMallOrderDetailMapper drtMallOrderDetailMapper;

	/**
	 * 保存
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 */
	public void insert(DrtMallOrderDetail drtMallOrderDetail) throws Exception{
		drtMallOrderDetailMapper.insert(drtMallOrderDetail);
	}

	/**
	 * 批量保存
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 */
	public void insertList(List<DrtMallOrderDetail> drtMallOrderDetailList) throws Exception{
		drtMallOrderDetailMapper.insertList(drtMallOrderDetailList);
	}

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 积分商城订单明细表 主键ID
	 * @throws Exception 出错抛出异常
	 */
	public void delete(String id) throws Exception{
		drtMallOrderDetailMapper.delete(id);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMallOrderDetail drtMallOrderDetail) throws Exception{
		drtMallOrderDetailMapper.update(drtMallOrderDetail);
	}

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 * @throws Exception 出错抛出异常
	 */
	public void updateIfNotNull(DrtMallOrderDetail drtMallOrderDetail) throws Exception{
		drtMallOrderDetailMapper.updateIfNotNull(drtMallOrderDetail);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单明细表 主键ID
	 * @return 积分商城订单明细表 单条记录
	 */
	public DrtMallOrderDetail selectByPrimaryKey(String id){
		return drtMallOrderDetailMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallOrderDetail 积分商城订单明细表
	 */
	public List<DrtMallOrderDetail> selectList(DrtMallOrderDetail drtMallOrderDetail){
		return drtMallOrderDetailMapper.selectList(drtMallOrderDetail);
	}

}