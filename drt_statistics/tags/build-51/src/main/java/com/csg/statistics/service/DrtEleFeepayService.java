
package com.csg.statistics.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.mapper.DrtEleFeepayMapper;
import com.csg.statistics.util.Md5Utils;

/**
 * Description：电费扣费帐单表Service
 * 
 * @version 1.0
 * @author 李达才
 * @since 2017-12-01
 *
 */
@Service
public class DrtEleFeepayService {
    
    /**
     * 扣款交易（文件）表的mapper
     */
    @Autowired
    DrtEleFeepayMapper drtEleFeepayMapper;
    
    /**
	 * 查询时间范围内扣款交易数据
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
    public List<Map<String, Object>> findListByCreatTime(Date startTime, Date endTime) {
    	List<Map<String, Object>> list = drtEleFeepayMapper.findListByCreatTime(startTime, endTime);
    	if(list!=null&&list.size()>0){
    		String str = "";
    		for(Map<String, Object> map : list){
    			str = map.get("YWSQBH")+"|"+map.get("JE")+"|"+map.get("FKFZHH")+"|"+map.get("SKFZHH")+"|"+map.get("SKFZHMC")+"|";
    			map.put("SIGN", Md5Utils.MD5Encode(str, "UTF-8").toUpperCase());
    		}
    	}
    	return list;
    }
}
