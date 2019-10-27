package com.akulinski.crimetivity.pointsaftyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PointsaftyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointsaftyserviceApplication.class, args);
	}

}
