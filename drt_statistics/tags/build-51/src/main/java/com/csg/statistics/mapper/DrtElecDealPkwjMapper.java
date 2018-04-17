/**
 * 
 */
package com.csg.statistics.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.csg.statistics.entity.YxPkwjtzxxBean;

/**
 * @author 李城
 *
 * 批扣文件Mapper
 */
public interface DrtElecDealPkwjMapper {
  
	
	/**
	 * 查询读取次数不超过3次的营销批扣文件通知信息
	 * 
	 * @return  读取次数不超过3次的营销批扣文件通知信息集合
	 * */
	@Select("SELECT * FROM  DRT_YXPKWJTZXX  WHERE DQCS<=3")
	public List<YxPkwjtzxxBean> queryYxPkwjtzxx();
}
