package com.mediconnect.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.mediconnect.service.auth", "com.mediconnect.service.common_entities"})
@ComponentScan(basePackages = {"com.mediconnect.service.auth", "com.mediconnect.service.common_entities"})
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
