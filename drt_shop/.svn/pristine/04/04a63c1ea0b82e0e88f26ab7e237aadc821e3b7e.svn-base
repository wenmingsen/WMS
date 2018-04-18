package com.csg.intshop.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csg.intshop.entity.DrtAdministrativeRegion;
import com.csg.intshop.entity.DrtShopAddress;
import com.csg.intshop.service.DrtAdministrativeRegionService;
/**
 * 
 * @author liulei3
 *查询省城市街道等信息Controller
 */
@Controller
@RequestMapping("/ins/DAR")
public class DrtAdministrativeRegionController {
	/**注入DrtAdministrativeRegionService*/
	private  DrtAdministrativeRegionService drtAdministrativeRegionService;
	
	public Map<String,Object> queryPCT(DrtShopAddress drtShopAddress){
		return drtAdministrativeRegionService.selectList(drtShopAddress);
	}
}
