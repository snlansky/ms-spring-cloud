package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient
@EnableTurbine
public class TurbineApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(TurbineApplication.class, args);
	}

}
