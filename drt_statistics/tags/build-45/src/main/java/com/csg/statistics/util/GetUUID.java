package com.csg.statistics.util;

import java.util.UUID;

/**
 * 获取uuid
 * */
public class GetUUID {
	
	public static String getUuuid(){
		UUID uuid = UUID.randomUUID();
		String id = String.valueOf(uuid);
		id = id.replace("-","");
		return  id;
	}
}
