package com.wipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMicroserviceApplication.class, args);
	}

}
