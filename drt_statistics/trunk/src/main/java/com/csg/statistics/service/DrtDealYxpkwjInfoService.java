/**
 * 
 */
package com.csg.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.YxPkwjtzxxBean;
import com.csg.statistics.mapper.DrtDealYxpkwjInfoMapper;

/**
 *  营销批扣文件通知电融通处理信息Service
 * 
 * @author 李城
 *
 */
@Service
@Transactional
public class DrtDealYxpkwjInfoService {

	/** 营销批扣文件通知电融通处理信息*/
	@Autowired
	private DrtDealYxpkwjInfoMapper drtDealYxpkwjInfoMapper;
	
	/**
	 * 新增营销批扣文件通知处理信息
	 * 
	 * @param yxPkwjtzxxBean 新增对象
	 * */
	public void  insYxPkwjtzxx(YxPkwjtzxxBean yxPkwjtzxxBean){
		drtDealYxpkwjInfoMapper.insYxPkwjtzxx(yxPkwjtzxxBean);
	}
	
	/**
	 * 删除营销批扣文件通知处理信息
	 * 
	 *@param id 删除对象id
	 * */
	public void  delYxPkwjtzxx(String id){
		drtDealYxpkwjInfoMapper.delYxPkwjtzxx(id);
	}
	
	/**
	 * 未放进队列的批扣文件信息读取次数加1。
	 * 
	 * @param id 修改对象id
	 * */
	public void updYxPkwjtzxx(String id){
		drtDealYxpkwjInfoMapper.updYxPkwjtzxx(id);
	}
	
}
