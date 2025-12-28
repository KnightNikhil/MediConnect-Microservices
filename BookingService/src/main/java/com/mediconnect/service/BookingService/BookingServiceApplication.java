package com.mediconnect.service.BookingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.mediconnect.service.BookingService", "com.mediconnect.service.common_entities"})  // ???
@ComponentScan(basePackages = {"com.mediconnect.service.BookingService", "com.mediconnect.service.common_entities"}) // ???
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

}
