package com.mediconnect.service.diagnosisCentre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.mediconnect.service.diagnosisCentre", "com.mediconnect.service.common_entities"})
@ComponentScan(basePackages = {"com.mediconnect.service.diagnosisCentre", "com.mediconnect.service.common_entities"})
@EnableFeignClients
public class DiagnosisServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosisServiceApplication.class, args);
	}

}
