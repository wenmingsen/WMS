package com.csg.intshop.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csg.intshop.entity.DrtMallCart;
import com.csg.intshop.mapper.DrtMallCartMapper;

/**
 * DrtMallCartService 积分商城购物车表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月23日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMallCartService{
	
	/**积分商城购物车表drtMallCartMapper接口*/
	@Autowired
	private DrtMallCartMapper drtMallCartMapper;

	/**
	 * 保存
	 * 
	 * @param drtMallCart 积分商城购物车表
	 */
	public void insert(DrtMallCart drtMallCart) throws Exception{
		drtMallCartMapper.insert(drtMallCart);
	}

	/**
	 * 批量保存
	 * 
	 * @param drtMallCart 积分商城购物车表
	 */
	public void insertList(List<DrtMallCart> drtMallCartList) throws Exception{
		drtMallCartMapper.insertList(drtMallCartList);
	}

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 积分商城购物车表 主键ID
	 * @throws Exception 出错抛出异常
	 */
	public void delete(String id) throws Exception{
		drtMallCartMapper.delete(id);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallCart 积分商城购物车表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMallCart drtMallCart) throws Exception{
		drtMallCartMapper.update(drtMallCart);
	}

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallCart 积分商城购物车表
	 * @throws Exception 出错抛出异常
	 */
	public void updateIfNotNull(DrtMallCart drtMallCart) throws Exception{
		drtMallCartMapper.updateIfNotNull(drtMallCart);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城购物车表 主键ID
	 * @return 积分商城购物车表 单条记录
	 */
	public DrtMallCart selectByPrimaryKey(String id){
		return drtMallCartMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallCart 积分商城购物车表
	 */
	public List<DrtMallCart> selectList(DrtMallCart drtMallCart){
		return drtMallCartMapper.selectList(drtMallCart);
	}

	/**
	 * 同步购物车记录
	 * @param updateList 待更新记录集
	 * @param insertList 待新增记录集
	 * @param deleteSet 待删除记录集
	 * @throws Exception 出错抛出异常
	 */
	public void synMallCart(List<DrtMallCart> updateList,
			List<DrtMallCart> insertList, Set<String> deleteSet) throws Exception {
		if(!CollectionUtils.isEmpty(updateList)){
			for (DrtMallCart entity : updateList) {
				this.updateIfNotNull(entity);
			}
		}
		if(!CollectionUtils.isEmpty(insertList)){
			this.insertList(insertList);
		}
		if(!CollectionUtils.isEmpty(deleteSet)){
			for (String cartId : deleteSet) {
				this.delete(cartId);
			}
		}
	}

}