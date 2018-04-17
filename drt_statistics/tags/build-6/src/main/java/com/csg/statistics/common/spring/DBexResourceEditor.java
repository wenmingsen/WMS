package com.csg.statistics.common.spring;

import org.springframework.core.io.ResourceEditor;

public class DBexResourceEditor extends ResourceEditor {
	
	public DBexResourceEditor(){
		super(new DBexResourceLoader(), null);
	}

}
