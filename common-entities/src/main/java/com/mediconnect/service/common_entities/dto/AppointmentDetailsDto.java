package com.mediconnect.service.common_entities.dto;

import com.mediconnect.service.common_entities.entity.Enums.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDetailsDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long patientId;
    private Long doctorId;
//    private Long diagnosisCentreId;
    private BookingStatus bookingStatus;
}
