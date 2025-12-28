package com.mediconnect.service.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.mediconnect.service.patient", "com.mediconnect.service.common_entities"}) // ???
@ComponentScan(basePackages = {"com.mediconnect.service.patient", "com.mediconnect.service.common_entities"}) // ???
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

}
