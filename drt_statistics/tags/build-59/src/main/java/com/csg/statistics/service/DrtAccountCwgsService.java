
package com.csg.statistics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.entity.DrtAccountCwgs;
import com.csg.statistics.mapper.DrtAccountCwgsMapper;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;

/**
 * Description：账户信息（文件）表Service
 * 
 * @version 1.0
 * @author 李达才
 * @since 2017-12-01
 *
 */
@Service
public class DrtAccountCwgsService {
    
    /**
     * 账户信息（文件）表的mapper
     */
    @Autowired
    DrtAccountCwgsMapper drtAccountCwgsMapper;
    
    /**
     * 保存账户信息（文件）表
     * @param drtAccountCwgs drtAccountCwgs
     * @return
     */
    public int insertAccountCwgs(List<Map<Integer,String>> data) {
    	int insertcount = 0;
    	if(data!=null&&data.size()>1){
    		List<DrtAccountCwgs> list = new ArrayList<DrtAccountCwgs>();
    		for(int i=1;i<data.size();i++){
    			Map<Integer, String> map = data.get(i);
    			DrtAccountCwgs drtAccountCwgs = new DrtAccountCwgs();
    			drtAccountCwgs.setDrtAccountId(UUIDUtil.generateUUID());
    			drtAccountCwgs.setAccountNumber(map.get(0));
    			drtAccountCwgs.setAccountName(map.get(1));
    			drtAccountCwgs.setClientNumber(map.get(2));
    			drtAccountCwgs.setClientName(map.get(3));
    			drtAccountCwgs.setStandbyOne(map.get(4));
    			drtAccountCwgs.setStandbyTwo(map.get(5));
    			drtAccountCwgs.setUpdateTime(DateTimeUtils.getCurrentTime());
    			list.add(drtAccountCwgs);
    		}
    		if(list.size()>0){
    			drtAccountCwgsMapper.deleteAccountCwgs(list);
    			insertcount = drtAccountCwgsMapper.insertAccountCwgsAll(list);
    		}
    	}
    	return insertcount;
    }
    
    /**
     * 更新扣款交易数据
     * @param data
     * @return
     */
    public int updateResultType(DrtAccountCwgs drtAccountCwgs) {
    	return drtAccountCwgsMapper.updateAccountCwgs(drtAccountCwgs);
    }
}
