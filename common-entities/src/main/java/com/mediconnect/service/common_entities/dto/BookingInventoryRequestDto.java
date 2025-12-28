package com.mediconnect.service.common_entities.dto;

import com.mediconnect.service.common_entities.entity.Enums.Role;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingInventoryRequestDto {
    private Role bookingFor;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;

}
