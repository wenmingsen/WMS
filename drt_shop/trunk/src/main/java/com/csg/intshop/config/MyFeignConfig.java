package com.csg.intshop.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.csg.common.config.AppConfig;
import com.csg.common.config.CommonContants;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * 每次调用feign设置请求头，把token带上做检验
 * 
 * @author Syni
 * 
 */
@Configuration
public class MyFeignConfig {
	@Bean
	public RequestInterceptor headerInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				// System.out.println(requestTemplate.request().getClass().getName());
				List<String> list = new ArrayList<String>();
				list.add(AppConfig.getProperty(CommonContants.feignToken,
						CommonContants.feignTokenValue));
				Map<String, Collection<String>> map2 = new HashedMap();
				map2.put(CommonContants.feignToken, list);
				requestTemplate.headers(map2);
			}
		};
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}
