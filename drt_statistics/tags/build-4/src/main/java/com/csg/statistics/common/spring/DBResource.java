package com.csg.statistics.common.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DBResource extends AbstractResource {
	Log log = LogFactory.getLog(getClass());
	private Properties properties;
	
	private Properties fileProperties;
	
	private String description;
	
	private String table="appconfig";
	private String url;
	private String username;
	private String password;
	
	public DBResource(String description){
		try{
			this.description=description;
			parseLocation();
			loadResource();
		}catch(Exception e){
			this.properties=null;
			throw new IllegalArgumentException("unavailable DBResource for["+description+"]",e);
		}
	}

	@Override
	public String getDescription() {
		return "DB resource["+description+"]";
	}
	
	public String getFilename() throws IllegalStateException {
		return "virtualFile:db.properties";
	}

	@Override
	public InputStream getInputStream() throws IOException {
		if(properties==null){
			throw new IOException("resource unavailable");
		}
		PipedInputStream is=new PipedInputStream();
		final PipedOutputStream os=new PipedOutputStream(is);
		new Thread(){
			public void run(){
				try{
					properties.store(new BufferedWriter(new OutputStreamWriter(os,"utf-8")),"virtual propertyFiles");
				}catch(Exception e){
					log.error(e.getMessage(),e);
				}finally{
					try {
						if(os!=null)
							os.close();
					} catch (Exception e) {
						log.error(e.getMessage(),e);
					}
				}
			}
		}.start();
		return is;
	}
	
	private void parseLocation() throws IOException, ClassNotFoundException {
		if(!description.startsWith("jdbc:")){
			ResourceLoader loader=new DefaultResourceLoader();
			Resource resource=loader.getResource(description);
			fileProperties=new Properties();
			new DefaultPropertiesPersister().load(fileProperties, new InputStreamReader(resource.getInputStream(),"utf-8"));
			this.url=fileProperties.getProperty("database.connection.url");
			if(this.url==null)
				throw new NestedIOException("required property is not found with key[database.connection.url]");
			this.username=fileProperties.getProperty("database.connection.username");
			if(this.username==null)
				throw new NestedIOException("required property is not found with key[database.connection.username]");

			this.password=fileProperties.getProperty("database.connection.password");
			if(this.password==null)
				throw new NestedIOException("required property is not found with key[database.connection.password]");

			String driver_class=fileProperties.getProperty("database.connection.driver_class");
			if(driver_class==null)
				throw new NestedIOException("required property is not found with key[database.connection.driver_class]");

			Class.forName(driver_class);
		}else{
			this.url=description;
		}
	}
	
	private void loadResource() throws SQLException {
		Connection conn=null;
		if(description.startsWith("jdbc:")){
			conn= DriverManager.getConnection(this.url);
		}else{
			conn= DriverManager.getConnection(this.url, this.username, this.password);
		}
		try{
			conn.setReadOnly(true);
			Statement statement=conn.createStatement();
			ResultSet rs=statement.executeQuery("select p_key,p_value from "+this.table);
			properties=new Properties();
			while(rs.next()){
				String key=rs.getString("p_key");
				String value=rs.getString("p_value")==null?"":rs.getString("p_value");
				properties.setProperty(key, value);
			}
			properties.putAll(fileProperties);
		}finally{
			conn.close();
		}
	}
}
