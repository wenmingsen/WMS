package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtNews;
import com.csg.statistics.mapper.DrtNewsMapper;


/**
 * DrtNewsService 消息表
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月06日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtNewsService{
	
	/**消息表drtNewsMapper接口*/
	@Autowired
	private DrtNewsMapper drtNewsMapper;

	/**
	 * 保存
	 * 
	 * @param drtNews 消息表
	 */
	public void insert(DrtNews drtNews) throws Exception{
		drtNewsMapper.insert(drtNews);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtNews 消息表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtNews drtNews) throws Exception{
		drtNewsMapper.update(drtNews);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param tid 消息表 主键ID
	 * @return 消息表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtNews selectByPrimaryKey(String tid) throws Exception{
		return drtNewsMapper.selectByPrimaryKey(tid);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtNews 消息表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtNews> selectList(DrtNews drtNews) throws Exception{
		return drtNewsMapper.selectList(drtNews);
	}

	/**
	 * 批量插入消息
	 * 
	 * @param lstDrtNews 消息表List
	 * @return Integer 影响条数
	 */
	public Integer insertDrtNews(List<DrtNews> lstDrtNews){
		return drtNewsMapper.insertDrtNews(lstDrtNews);
	}
	
	/**
	 * 删除最近一周的站内发送消息记录 
	 * @param drtNewsBean
	 * @return Integer 影响条数
	 */
	public Integer deleteWeekNews(){
		return drtNewsMapper.deleteWeekNews();
	}
}