package com.csg.intshop.mapper;



import java.util.List;

import com.csg.intshop.entity.DrtAdministrativeRegion;
import com.csg.intshop.entity.DrtShopAddress;


/**
 * DrtAdministrativeRegionMapper 
 *
 * @author  刘磊
 * @since   1.8
 * @version 2017年12月28日 刘磊
 */
public interface DrtAdministrativeRegionMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtAdministrativeRegion 
	 */
	int insert(DrtAdministrativeRegion drtAdministrativeRegion);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtAdministrativeRegion 
	 */
	int update(DrtAdministrativeRegion drtAdministrativeRegion);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param administrativeRegionId  主键ID
	 * @return  单条记录
	 */
	DrtAdministrativeRegion selectByPrimaryKey(DrtAdministrativeRegion administrativeRegion);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtAdministrativeRegion 
	 * @return  记录集
	 */
	List<DrtAdministrativeRegion> selectList(DrtShopAddress drtShopAddress);

}