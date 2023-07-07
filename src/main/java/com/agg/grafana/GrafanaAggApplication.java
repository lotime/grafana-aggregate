package com.agg.grafana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangzhimin
 */
@EnableFeignClients
@SpringBootApplication
public class GrafanaAggApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrafanaAggApplication.class, args);
	}

}
