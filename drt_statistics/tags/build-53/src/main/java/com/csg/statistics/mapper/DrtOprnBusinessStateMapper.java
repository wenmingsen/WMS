/**
 * 
 */
package com.csg.statistics.mapper;

import com.csg.statistics.entity.DrtStateBusinessUser;
import com.csg.statistics.entity.StatistiseBusinessUserBean;



/**
 * 业务统计dao接口
 * 
 * @author 李城
 *
 */
public interface DrtOprnBusinessStateMapper {

	/**查询新增用户日统计*/
	public int queryNewUserDayCount(StatistiseBusinessUserBean bean);
	
	/**查询实名认证日统计*/
	public int queryIdcardDayCount(StatistiseBusinessUserBean bean);
	
	/**查询活跃度日统计*/
	public int queryRecordDayCount(StatistiseBusinessUserBean bean);
	
	/**查询绑定银行卡日统计*/
	public int  queryBangkDayCount(StatistiseBusinessUserBean bean);
	
	/**查询业务用户周，月统计*/
	public void  insBusinessUser(DrtStateBusinessUser drtStateBusinessUser);
}
