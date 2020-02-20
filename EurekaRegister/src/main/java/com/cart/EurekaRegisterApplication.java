package com.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableEurekaServer
@CrossOrigin(origins = "http://localhost:4200")
public class EurekaRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRegisterApplication.class, args);
	}

}
