package com.mediconnect.service.BookingService.service;

import com.mediconnect.service.common_entities.entity.BookingInventory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingEventProducer {

    private final KafkaTemplate kafkaTemplate;

    private static final String topic = "booking-created-topic";

    public void publishBookingCreatedEvent(BookingInventory bookingInventory){
        kafkaTemplate.send(topic, bookingInventory.getId().toString() ,bookingInventory);
    }

}
