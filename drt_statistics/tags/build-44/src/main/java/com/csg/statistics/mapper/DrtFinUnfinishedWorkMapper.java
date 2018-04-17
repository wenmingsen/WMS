/**
 * 
 */
package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtFinUnfinishedWork;

/**
 * 资产待办项Mapper
 * 
 * @author 李城
 *
 */

public interface DrtFinUnfinishedWorkMapper {

	/**
	 * 获取可执行的，有效地资产待办项
	 * 
	 * @param drtFinUnfinishedWork 参数对象
	 * @return 可执行的，有效地资产待办项集合
	 * */
	public  List<DrtFinUnfinishedWork> queryDrtFinUnfinishedWorks(DrtFinUnfinishedWork drtFinUnfinishedWork);
}
