package com.csg.intshop.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by yangbinjun on 2017/11/10.
 */
public class UUIDUtil {

	//自动生成主键
    public static synchronized String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    public static String getElecLSHByDate(){
    	Date date = new Date();
    	String reqssn = "drtElec"+DateTimeUtils.converDateToString(date,
				"yyyyMMddHHmmssSSSSSS");
    	Random random = new Random();
		int next = random.nextInt(90000) + 10000;
		return reqssn + next;
    }
}
