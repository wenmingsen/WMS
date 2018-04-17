package com.csg.statistics.util;

import java.util.UUID;

/**
 * Created by yangbinjun on 2017/11/10.
 */
public class UUIDUtil {

	//自动生成主键
    public static synchronized String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
