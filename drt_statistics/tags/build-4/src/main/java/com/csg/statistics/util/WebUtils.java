package com.csg.statistics.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

public class WebUtils extends org.springframework.web.util.WebUtils {

    @SuppressWarnings("unchecked")
    public static <V> V getParamsInMap(Map<String, Object> map, String key, Class<V> clazz) {
        if (map != null) {
            Object o = map.get(key);
            if (clazz.isInstance(o)) {
                return (V) o;
            }
        }
        return null;
    }

    public static boolean checkParamsIsNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return true;
            }
            if (obj instanceof String) {
                if (StringUtils.isBlank(obj.toString())) {
                    return true;
                }
            }
            if (obj instanceof Collection) {
                if (CollectionUtils.isEmpty((Collection<?>) obj)) {
                    return true;
                }
            }
        }
        return false;
    }

}
