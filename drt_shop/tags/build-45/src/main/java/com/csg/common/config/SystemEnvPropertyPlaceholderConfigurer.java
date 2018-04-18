package com.csg.common.config;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class SystemEnvPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private File appRoot;

	private String fromDBLoadAppconfig;

	public File getAppRoot() {
		return appRoot;
	}

	public void setAppRoot(File appRoot) {
		this.appRoot = appRoot;
		AppConfig.setLocalCtxPath(appRoot.getPath());
	}

	public void setFromDBLoadAppconfig(String fromDBLoadAppconfig) {
		this.fromDBLoadAppconfig = fromDBLoadAppconfig;
	}

	protected String resolveSystemProperty(String key) {
		String result = super.resolveSystemProperty(key);
		if (result == null) {
			return (String) AppConfig.getEnvironments().get(key);
		}
		return result;
	}

	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		AppConfig.addProperties(props);
		if ("true".equals(fromDBLoadAppconfig)) {
			AppConfig.getPropertiesFromDB(props);
		}
	}

}
