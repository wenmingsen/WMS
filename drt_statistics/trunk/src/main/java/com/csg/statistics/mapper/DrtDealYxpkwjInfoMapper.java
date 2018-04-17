/**
 * 
 */
package com.csg.statistics.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.csg.statistics.entity.YxPkwjtzxxBean;

/**
 * 营销批扣文件通知电融通处理信息服务层
 * 
 * @author 李城
 *
 */

public interface DrtDealYxpkwjInfoMapper {

	
	/**
	 * 新增营销批扣文件通知处理信息
	 * 
	 * @param yxPkwjtzxxBean 新增对象
	 * */
	@Insert("INSERT INTO DRT_YXPKWJTZXX VALUES (#{id},#{DSDWBH},#{CZYBM},#{DKWJMC},#{WJLJ},#{JYBS},#{JYJE},#{DZLB},#{JYRQ},#{SFRQ},#{DQCS})")
	public void  insYxPkwjtzxx(YxPkwjtzxxBean yxPkwjtzxxBean);
	
	/**
	 * 删除营销批扣文件通知处理信息
	 * 
	 *@param id 删除对象id
	 * */
	@Delete("DELETE FROM DRT_YXPKWJTZXX  WHERE id=#{id}")
	public void  delYxPkwjtzxx(String id);
	
	/**
	 * 未放进队列的批扣文件信息读取次数加1。
	 * 
	 * @param id 修改对象id
	 * */
	@Update("UPDATE DRT_YXPKWJTZXX SET DQCS=(DQCS+1) WHERE id=#{id}")
	public void updYxPkwjtzxx(String id);
}
