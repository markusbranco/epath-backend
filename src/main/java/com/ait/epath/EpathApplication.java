package com.ait.epath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.ait.epath.model"})
@EnableSpringDataWebSupport
@EnableCaching
public class EpathApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpathApplication.class, args);
	}

}
