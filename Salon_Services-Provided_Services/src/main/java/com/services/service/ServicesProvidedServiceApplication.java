package com.services.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan({"com.services.service", "com.security.library"})
public class ServicesProvidedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesProvidedServiceApplication.class, args);
	}

}
