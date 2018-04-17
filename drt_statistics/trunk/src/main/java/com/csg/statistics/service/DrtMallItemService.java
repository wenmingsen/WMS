package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csg.statistics.entity.DrtMallItem;
import com.csg.statistics.mapper.DrtMallItemMapper;


/**
 * DrtMallItemService 积分商城商品池
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月24日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMallItemService{
	
	/**积分商城商品池drtMallItemMapper接口*/
	@Autowired
	private DrtMallItemMapper drtMallItemMapper;

	/**
	 * 保存
	 * 
	 * @param drtMallItem 积分商城商品池
	 */
	public void insert(DrtMallItem drtMallItem) throws Exception{
		drtMallItemMapper.insert(drtMallItem);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMallItem 积分商城商品池
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMallItem drtMallItem) throws Exception{
		drtMallItemMapper.update(drtMallItem);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城商品池 主键ID
	 * @return 积分商城商品池 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMallItem selectByPrimaryKey(String id) throws Exception{
		return drtMallItemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMallItem 积分商城商品池
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtMallItem> selectList(DrtMallItem drtMallItem) throws Exception{
		return drtMallItemMapper.selectList(drtMallItem);
	}
	
	/**
	 * 批量保存
	 * 
	 * @param drtMallItem 积分商城商品池
	 */
	public void insertList(List<DrtMallItem> drtMallItemList) throws Exception{
		drtMallItemMapper.insertList(drtMallItemList);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param strSkuCode 商品编号
	 * @return 积分商城商品池 记录集
	 */
	public List<DrtMallItem> selectListBySkuCodes(String strSkuCode){
		return drtMallItemMapper.selectListBySkuCodes(strSkuCode);
	}
	
	/**
	 * 同步积分商城商品池
	 * @param updateList 待更新记录集
	 * @param insertList 待新增记录集
	 * @throws Exception 出错抛出异常
	 */
	public void synMallItem(List<DrtMallItem> updateList,
			List<DrtMallItem> insertList) throws Exception {
		if(!CollectionUtils.isEmpty(updateList)){
			for (DrtMallItem entity : updateList) {
				this.update(entity);
			}
		}
		if(!CollectionUtils.isEmpty(insertList)){
			this.insertList(insertList);
		}
	}
}