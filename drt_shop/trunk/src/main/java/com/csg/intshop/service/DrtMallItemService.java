
package com.csg.intshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallItemQuery;
import com.csg.intshop.mapper.DrtMallItemMapper;

/**
 * DrtMallItemService 积分商城商品池
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2018年01月23日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMallItemService {
    
    /** 积分商城商品池drtMallItemMapper接口 */
    @Autowired
    private DrtMallItemMapper drtMallItemMapper;
    
    /**
     * 保存
     * 
     * @param drtMallItem 积分商城商品池
     */
    public void insert(DrtMallItem drtMallItem) throws Exception {
        drtMallItemMapper.insert(drtMallItem);
    }
    
    /**
     * 批量保存
     * 
     * @param drtMallItem 积分商城商品池
     */
    public void insertList(List<DrtMallItem> drtMallItemList) throws Exception {
        drtMallItemMapper.insertList(drtMallItemList);
    }
    
    /**
     * 通过主键删除单条记录
     * 
     * @param id 积分商城商品池 主键ID
     * @throws Exception 出错抛出异常
     */
    public void delete(String id) throws Exception {
        drtMallItemMapper.delete(id);
    }
    
    /**
     * 更新
     * <p>
     * 通过主键更新记录
     * </p>
     * 
     * @param drtMallItem 积分商城商品池
     * @throws Exception 出错抛出异常
     */
    public void update(DrtMallItem drtMallItem) throws Exception {
        drtMallItemMapper.update(drtMallItem);
    }
    
    /**
     * 更新非空字段
     * <p>
     * 通过主键更新记录
     * </p>
     * 
     * @param drtMallItem 积分商城商品池
     * @throws Exception 出错抛出异常
     */
    public void updateIfNotNull(DrtMallItem drtMallItem) throws Exception {
        drtMallItemMapper.updateIfNotNull(drtMallItem);
    }
    
    /**
     * 更新非空字段
     * <p>
     * 通过skuCode更新记录
     * </p>
     * 
     * @param drtMallItem 积分商城商品池
     * @throws Exception 出错抛出异常
     */
    public void updateBySkuCode(DrtMallItem drtMallItem) throws Exception {
        drtMallItemMapper.updateBySkuCode(drtMallItem);
    }
    
    /**
     * 通过主键获取单条记录
     * 
     * @param id 积分商城商品池 主键ID
     * @return 积分商城商品池 单条记录
     */
    public DrtMallItem selectByPrimaryKey(String id) {
        return drtMallItemMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtMallItem 积分商城商品池
     */
    public List<DrtMallItem> selectList(DrtMallItem drtMallItem) {
        return drtMallItemMapper.selectList(drtMallItem);
    }
    
    /**
     * 根据商品SKU编号集合查询数据
     * 
     * @param map 参数
     * @return 积分商城商品池 记录集
     */
    public List<DrtMallItem> selectListByinterface(Map<String, Object> map) {
        return drtMallItemMapper.selectListByinterface(map);
    }
    
    /**
     * 根据id更新商品状态
     * 
     * @param drtMallItem 积分商城商品池
     * @return 更新条数
     */
    public int updateBatchByinterface(List<DrtMallItem> list) {
        return drtMallItemMapper.updateBatchByinterface(list);
    }
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtMallItemQuery 积分商城商品池-查询类
     * @return 积分商城商品池 记录集
     */
	public List<DrtMallItem> selectListByDrtMallItemQuery(DrtMallItemQuery drtMallItemQuery){
		return drtMallItemMapper.selectListByDrtMallItemQuery(drtMallItemQuery);
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