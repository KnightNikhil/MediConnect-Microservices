package com.mediconnect.service.BookingService.service;

import com.mediconnect.service.common_entities.dto.AppointmentDetailsDto;
import com.mediconnect.service.common_entities.dto.BookingInventoryRequestDto;

import java.util.List;

public interface InventoryService {
    void bookDoctorAppointment(AppointmentDetailsDto appointmentDetailsDto);

    void bookDCAppointment(AppointmentDetailsDto appointmentDetailsDto);

    List<AppointmentDetailsDto> findMyAppointments(Long patientId);

    void addInventory(BookingInventoryRequestDto bookingInventoryRequestDto);
}
