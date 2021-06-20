package com.theWalkingshoe.coudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class CoudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoudGatewayApplication.class, args);
	}

}
