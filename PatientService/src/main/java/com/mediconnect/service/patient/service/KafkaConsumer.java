package com.mediconnect.service.patient.service;

import com.mediconnect.service.common_entities.entity.BookingInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {


    @KafkaListener(topics = "booking-created-topic",
    groupId = "notification-service")
    public void bookingConfirmation(BookingInventory bookingInventory){
        System.out.println(
                "--------BOOKING CREATED-----------"
        );
        System.out.println(bookingInventory);
    }
}
