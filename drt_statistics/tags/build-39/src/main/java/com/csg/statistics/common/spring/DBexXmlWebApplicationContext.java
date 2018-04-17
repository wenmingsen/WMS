package com.csg.statistics.common.spring;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class DBexXmlWebApplicationContext extends XmlWebApplicationContext {
	
	String DB_URL_PREFIX = "db:";
	
	public Resource getResource(String location) {
		Assert.notNull(location, "Location must not be null");
		if (location.startsWith(DB_URL_PREFIX)) {
			return new DBResource(location.substring(DB_URL_PREFIX.length()));
		}
		return super.getResource(location);
	}
}
