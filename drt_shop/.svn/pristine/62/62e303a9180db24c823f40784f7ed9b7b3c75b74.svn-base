
package com.csg.intshop.mapper;

import java.util.List;
import java.util.Map;

import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallItemQuery;

/**
 * DrtMallItemMapper 积分商城商品池
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2018年01月23日 曾令鹏
 */
public interface DrtMallItemMapper {
    
    /**
     * 保存
     * 
     * @param drtMallItem 积分商城商品池
     */
    void insert(DrtMallItem drtMallItem);
    
    /**
     * 批量保存
     * 
     * @param drtMallItem 积分商城商品池
     */
    void insertList(List<DrtMallItem> drtMallItemList);
    
    /**
     * 通过主键删除单条记录
     * 
     * @param id 积分商城商品池 主键ID
     */
    void delete(String id);
    
    /**
     * 更新
     * <p>
     * 通过主键更新记录
     * </p>
     * 
     * @param drtMallItem 积分商城商品池
     */
    void update(DrtMallItem drtMallItem);
    
    /**
     * 更新非空字段
     * <p>
     * 通过主键更新记录
     * </p>
     * 
     * @param drtMallItem 积分商城商品池
     */
    void updateIfNotNull(DrtMallItem drtMallItem);
    
    /**
     * 更新非空字段
     * <p>
     * 通过skuCode键更新记录
     * </p>
     * 
     * @param drtMallItem 积分商城商品池
     */
    void updateBySkuCode(DrtMallItem drtMallItem);
    
    /**
     * 通过主键获取单条记录
     * 
     * @param id 积分商城商品池 主键ID
     * @return 积分商城商品池 单条记录
     */
    DrtMallItem selectByPrimaryKey(String id);
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtMallItem 积分商城商品池
     * @return 积分商城商品池 记录集
     */
    List<DrtMallItem> selectList(DrtMallItem drtMallItem);
    
    /**
     * 根据商品SKU编号集合查询数据
     * 
     * @param map 参数
     * @return 积分商城商品池 记录集
     */
    List<DrtMallItem> selectListByinterface(Map<String, Object> map);
    
    /**
     * 根据id更新商品状态
     * 
     * @param drtMallItem 积分商城商品池
     * @return 更新条数
     */
    int updateBatchByinterface(List<DrtMallItem> list);
    
    /**
     * 通过自定义非空字段获取记录集
     * 
     * @param drtMallItemQuery 积分商城商品池-查询类
     * @return 积分商城商品池 记录集
     */
    List<DrtMallItem> selectListByDrtMallItemQuery(DrtMallItemQuery drtMallItemQuery);
    
}
