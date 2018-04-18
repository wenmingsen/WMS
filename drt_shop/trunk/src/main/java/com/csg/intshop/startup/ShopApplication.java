package com.csg.intshop.startup;

import org.apache.logging.log4j.core.config.Scheduled;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.csg.intshop"})
@EnableTransactionManagement
@MapperScan(value={"com.csg.intshop.mapper","com.csg.common.mapper"})
@ServletComponentScan(basePackages = "com.csg.intshop.controller")
@PropertySource(value={"file:${IM800_CONF_DIR}/drtIntShop.properties"})
@EnableDiscoveryClient
@Scheduled
@EnableFeignClients(basePackages={"com.csg.intshop.service.core"})
@EnableHystrix
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
}
