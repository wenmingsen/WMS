package com.csg.intshop.startup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Description: 应用程序配置，加载spring applicationContext-*.xml
 * Company: Syni
 *
 * @author 杨彬俊
 * @version 1.0
 * @since 2017-11-03
 */
@Configuration
@ImportResource(locations={"classpath:applicationContext-resource.xml"})
public class ApplicationConfig {
}
