
package com.csg.statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.entity.DrtClientCwgs;
import com.csg.statistics.mapper.DrtClientCwgsMapper;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;

/**
 * Description：客户信息（文件）表 Service
 * 
 * @version 1.0
 * @author 李达才
 * @since 2017-12-01
 *
 */
@Service
public class DrtClientCwgsService {
    
    /**
     * 客户信息（文件）表 的mapper
     */
    @Autowired
    DrtClientCwgsMapper drtClientCwgsMapper;
    
    /**
     * 保存账户信息（文件）表
     * @param data data
     * @return
     */
    public int insertClientCwgs(List<Map<Integer,String>> data) {
    	int insertcount = 0;
    	if(data!=null&&data.size()>0){
    		List<DrtClientCwgs> dccs = new ArrayList<DrtClientCwgs>();
    		for(int i=1;i<data.size();i++){
    			Map<Integer, String> map = data.get(i);
    			//drtClientCwgsMapper.deleteByClientNumber(map.get(1));
    			DrtClientCwgs drtClientCwgs = new DrtClientCwgs();
    			drtClientCwgs.setDrtClientId(UUIDUtil.generateUUID());
    			drtClientCwgs.setOfficeNumber(Integer.parseInt(map.get(0)));
    			drtClientCwgs.setClientNumber(map.get(1));
    			drtClientCwgs.setClientName(map.get(2));
    			drtClientCwgs.setStandbyOne(map.get(3));
    			drtClientCwgs.setStandbyTwo(map.get(4));
    			drtClientCwgs.setUpdateTime(DateTimeUtils.getCurrentTime());
    			dccs.add(drtClientCwgs);
    			//insertcount += drtClientCwgsMapper.insertClientCwgs(drtClientCwgs);
    		}
    		if(dccs.size()>0){
    			drtClientCwgsMapper.deleteClientCwgs(dccs);
    			insertcount = drtClientCwgsMapper.insertClientCwgsAll(dccs);
    		}
    	}
    	return insertcount;
    }
    
    /**
     * 更新客户信息（文件）表
     * @param drtClientCwgs
     * @return
     */
    public int updateResultType(DrtClientCwgs drtClientCwgs) {
    	return drtClientCwgsMapper.updateClientCwgs(drtClientCwgs);
    }
}
