package com.csg.statistics.startup;

import org.apache.logging.log4j.core.config.Scheduled;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.csg.statistics")
@EnableTransactionManagement
@MapperScan("com.csg.statistics.mapper")
@ServletComponentScan(basePackages = "com.csg.statistics.controller")
@PropertySource(value={"file:${IM800_CONF_DIR}/drtStatisticsCfg.properties"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages="com.csg.statistics.service.finance")
@Scheduled
public class StatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}
}
