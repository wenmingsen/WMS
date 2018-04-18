package com.csg.intshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtAdministrativeRegion;
import com.csg.intshop.entity.DrtShopAddress;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtAdministrativeRegionMapper;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;
import com.github.pagehelper.StringUtil;



/**
 * DrtAdministrativeRegionService 
 *
 * @author  刘磊
 * @since   1.8
 * @version 2017年12月28日 刘磊
 */
@Transactional
@Service
public class DrtAdministrativeRegionService{
	private Logger log = LoggerFactory.getLogger(DrtAdministrativeRegionService.class);

	
	/**drtAdministrativeRegionMapper接口*/
	@Autowired
	private DrtAdministrativeRegionMapper drtAdministrativeRegionMapper;

	/**
	 * 保存
	 * 
	 * @param drtAdministrativeRegion 
	 */
	public void insert(DrtAdministrativeRegion drtAdministrativeRegion) throws Exception{
		drtAdministrativeRegionMapper.insert(drtAdministrativeRegion);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtAdministrativeRegion 
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtAdministrativeRegion drtAdministrativeRegion) throws Exception{
		drtAdministrativeRegionMapper.update(drtAdministrativeRegion);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param administrativeRegionId  主键ID
	 * @return  单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtAdministrativeRegion selectByPrimaryKey(DrtAdministrativeRegion drtAdministrativeRegion) throws Exception{
		return drtAdministrativeRegionMapper.selectByPrimaryKey(drtAdministrativeRegion);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtAdministrativeRegion 
	 * @throws Exception 出错抛出异常
	 */
	public Map<String,Object> selectList(DrtShopAddress drtShopAddress){
		Map<String, Object> objMap= null;
		try{
			List<List<DrtAdministrativeRegion>> list = new ArrayList<>(); 
			List<DrtAdministrativeRegion> listP = null;
			List<DrtAdministrativeRegion> listC = null;
			List<DrtAdministrativeRegion> listT = null;
			List<DrtAdministrativeRegion> listPCT = null;
			String addressLevel1Id = drtShopAddress.getAddressLevel1Id();
			String addressLevel2Id = drtShopAddress.getAddressLevel2Id();
			listPCT = drtAdministrativeRegionMapper.selectList(drtShopAddress);
			if(StringUtil.isEmpty(addressLevel1Id)&&StringUtil.isEmpty(addressLevel2Id)){
				listP = listPCT ;
			}
			if(StringUtil.isNotEmpty(addressLevel1Id)){
				listC = listPCT ;
			}
			if(StringUtil.isNotEmpty(addressLevel2Id)){
				listT = listPCT;
			}
			list.add(listP);
			list.add(listC);
			list.add(listT);
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "查询成功", list);
		}catch(Exception e){			
			log.error("DrtAdministrativeRegionService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加失败", "");
			return objMap;
		}
		return objMap;
		
	}

}