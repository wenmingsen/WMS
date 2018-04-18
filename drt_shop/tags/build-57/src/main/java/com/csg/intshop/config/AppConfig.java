package com.csg.intshop.config;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import org.apache.tools.ant.taskdefs.Execute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.csg.common.config.BeanFactory;

public class AppConfig {
	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	// 配置参数
	public static final String DATA_PATH_DATA_HOME = "data_path.data_home";
	public static final String DATA_HOME = "user_data";

	private static Properties environments = null;
	private static String localCtxPath = null;
	private static String data_home = null;

	private static String table = "appconfig";

	static Properties properties = new Properties();
	static Properties dbProperties = new Properties(); // 存放在数据库配置表里的配置项

	static long updateTime = 0;

	public static void updateFromDB() {
		String sIntverval = properties.getProperty("updateAppconfigTime", "5");// 配置缓存生命周期为5分钟
		int interval = Integer.parseInt(sIntverval) * 60 * 1000;
		// JdbcTemplate jdbcTemplate = (JdbcTemplate)
		// BeanFactory.getBean("jdbcTemplate");
		NamedParameterJdbcTemplate jdbcTemplate = (NamedParameterJdbcTemplate) BeanFactory.getBean("namedParameterJdbcTemplate");
		if (System.currentTimeMillis() - updateTime > interval) { // 配置缓存生命周期为5分钟
			// 15分钟后才用常规的SPRING+HIBERNATE查数据库
			if (null != jdbcTemplate) {
				List<Map<String, Object>> appList = jdbcTemplate.queryForList("select * from appconfig", new HashMap<String, String>());
				for (Map<String, Object> appconfig : appList) {
					if (null != appconfig.get("p_key") && null != appconfig.get("pvalue")) {
						String pKey = (String) appconfig.get("p_key");
						String pValue = (String) appconfig.get("p_value");
						dbProperties.setProperty(pKey, pValue);
					}
				}
				properties.putAll(dbProperties);
				updateTime = System.currentTimeMillis();
			}
		}

	}

	public static void getPropertiesFromDB(Properties prop) {
		try {
			loadPropertiesFromDB(); // 从数据库配置表加载配置项
			prop.putAll(properties); // 将配置项写会给SPRING，这就意味着数据库配置项更优先，会覆盖配置文件的配置项
			updateTime = System.currentTimeMillis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}

	}

	public static void addProperties(Properties properties) {
		AppConfig.properties.putAll(properties);
	}

	public static void addProperty(String key, String value) {
		properties.put(key, value);
	}

	public static void removeProperty(String key) {
		properties.remove(key);
	}

	/**
	 * load the environment values
	 */
	@SuppressWarnings("rawtypes")
	public static Properties getEnvironments() {
		if (environments == null) {
			environments = new Properties();
			Vector osEnv = Execute.getProcEnvironment();
			for (Enumeration e = osEnv.elements(); e.hasMoreElements();) {
				String entry = (String) e.nextElement();
				int pos = entry.indexOf('=');
				if (pos == -1) {
					log.info("Ignoring: " + entry);
				} else {
					environments.put(entry.substring(0, pos), entry.substring(pos + 1));
				}
			}
		}
		return environments;
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}

    public static Boolean getProperty(String key, boolean defaultValue) {
        String value = getProperty(key, null);
        if(value != null && ("True".equals(value) || "true".equals(value))
                || "T".equals(value) || "t".equals(value)) {
            return true;
        }
        return false;
    }

    public static Integer getProperty(String key, int defaultValue) {
        String value = getProperty(key, null);
        if(value != null && !"".equals(value)) {
            return Integer.parseInt(value);
        }
        return defaultValue;
    }

	public static String getProperty(String key, String defaultValue) {

		updateFromDB();

		return properties.getProperty(key, defaultValue);
	}

	public static String getPropertyEncoding(String key, String encoding) {

		return getPropertyEncoding(key, encoding, null);
	}

	public static String getPropertyEncoding(String key, String encoding, String defaultValue) {
		String str = getProperty(key, defaultValue);
		try {
			// 进行编码转换，解决问题
			str = new String(str.getBytes("ISO8859-1"), encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
		}
		return str;
	}

	public static String getLocalCtxPath() {
		return localCtxPath;
	}

	public static void setLocalCtxPath(String _localCtxPath) {
		localCtxPath = _localCtxPath;
	}

	public static String getDataHome() {

		if (null == data_home) {
			String _data_home = properties.getProperty(DATA_PATH_DATA_HOME);
			if (null != _data_home && !"".equals(_data_home))
				data_home = _data_home;
			else
				data_home = localCtxPath + "/" + DATA_HOME;
		}

		return data_home;
	}

	private static void loadPropertiesFromDB() throws Exception {
		// 从数据库加载，因为在程序启动时SPRING上下文还不能用，所以不能使用常规的SPRING+HIBERNATE查数据库，只能用最原始的JDBC
		String url;
		String username;
		String password;

		url = properties.getProperty("spring.datasource.url");
		if (url == null)
			throw new NestedIOException("required property is not found with key[database.connection.url]");
		username = properties.getProperty("spring.datasource.username");
		if (username == null)
			throw new NestedIOException("required property is not found with key[database.connection.username]");

		password = properties.getProperty("spring.datasource.password");
		if (password == null)
			throw new NestedIOException("required property is not found with key[database.connection.password]");

		String driver_class = properties.getProperty("spring.datasource.driverClassName");
		if (driver_class == null)
			throw new NestedIOException("required property is not found with key[database.connection.driver_class]");

		Class.forName(driver_class);

		Connection conn = null;

		conn = DriverManager.getConnection(url, username, password);

		try {
			conn.setReadOnly(true);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select p_key,p_value from " + table);

			while (rs.next()) {
				String key = rs.getString("p_key");
				String value = rs.getString("p_value") == null ? "" : rs.getString("p_value");
				properties.setProperty(key, value);
			}
			// properties.putAll(fileProperties);

			Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				log.info("###key:" + key + " ###value:" + value);
			}
		} finally {
			conn.close();
		}
	}
}