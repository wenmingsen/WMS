package com.csg.intshop.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class ObjectUtils extends org.springframework.util.ObjectUtils {
	
	public static <V> V getObjectFromMap(Map<String,Object> map,Class<V> clazz) throws InstantiationException, IllegalAccessException {
		V obj = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			String fieldName = field.getName();
			if(map.containsKey(fieldName)){
				field.setAccessible(true);
				field.set(obj, map.get(fieldName));
			}
		}
		return obj;
	}
	
	public static <V> V getObjectFromMap(Map<String,Object> map,Class<V> clazz,Collection<String> paramNames)
			throws InstantiationException, IllegalAccessException {
		V obj = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			String fieldName = field.getName();
			if(map.containsKey(fieldName) && paramNames.contains(fieldName)){
				field.setAccessible(true);
				field.set(obj, map.get(fieldName));
			}
		}
		return obj;
	}
	
	public static <V> V setFieldsByMap(Map<String,Object> map,V obj,Collection<String> paramNames)
			throws InstantiationException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field:fields){
			String fieldName = field.getName();
			if(map.containsKey(fieldName) && paramNames.contains(fieldName)){
				field.setAccessible(true);
				field.set(obj, map.get(fieldName));
			}
		}
		return obj;
	}
	
	public static <V> V getObjectFromMap(Map<String,Object> map,Class<V> clazz,Collection<String> paramNames,
			Collection<String> excludeNames)
			throws InstantiationException, IllegalAccessException {
		V obj = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields){
			String fieldName = field.getName();
			if(map.containsKey(fieldName) && paramNames.contains(fieldName)){
				if(excludeNames.contains(fieldName)){
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(fieldName));
			}
		}
		return obj;
	}

}
