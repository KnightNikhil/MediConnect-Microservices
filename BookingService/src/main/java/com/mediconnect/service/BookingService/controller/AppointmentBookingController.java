package com.mediconnect.service.BookingService.controller;

import com.mediconnect.service.BookingService.service.InventoryService;
import com.mediconnect.service.common_entities.dto.AppointmentDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/booking")
public class AppointmentBookingController {

    private final InventoryService inventoryService;

    @PostMapping("/bookDoctorAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    public void bookDoctorAppointment(@RequestBody AppointmentDetailsDto appointmentDetailsDto){
        inventoryService.bookDoctorAppointment(appointmentDetailsDto);
    }

//    @PostMapping("/bookDiagnosisAppointment")
//    @PostMapping("/findMyAppointments")
//    @PatchMapping("/cancelAppointment")


}
