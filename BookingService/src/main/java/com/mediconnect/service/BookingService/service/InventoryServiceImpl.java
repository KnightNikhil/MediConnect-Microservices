package com.mediconnect.service.BookingService.service;

import com.mediconnect.service.BookingService.repository.BookingRepository;
import com.mediconnect.service.common_entities.dto.AppointmentDetailsDto;
import com.mediconnect.service.common_entities.dto.BookingInventoryRequestDto;
import com.mediconnect.service.common_entities.entity.BookingInventory;
import com.mediconnect.service.common_entities.entity.Enums.BookingStatus;
import com.mediconnect.service.common_entities.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final BookingRepository bookingRepository;
    
    private final ModelMapper modelMapper;

    @Override
    public void bookDoctorAppointment(AppointmentDetailsDto appointmentDetailsDto) {
        BookingInventory bookingInventory = bookingRepository.findByDoctorIdAndDateAndTime(appointmentDetailsDto.getDoctorId(), appointmentDetailsDto.getDate(), appointmentDetailsDto.getTime());
        if(bookingInventory.getBookingStatus().equals(BookingStatus.AVAILABLE)){
            bookingInventory.setBookingStatus(BookingStatus.CONFIRMED);
            bookingInventory.setPatientId(bookingInventory.getPatientId());
            bookingRepository.save(bookingInventory);
        }

    }

    @Override
    public void bookDCAppointment(AppointmentDetailsDto appointmentDetailsDto) {

    }

    @Override
    public List<AppointmentDetailsDto> findMyAppointments(Long patientId) {
        return List.of();
    }

    @Override
    public void addInventory(BookingInventoryRequestDto bookingInventoryRequestDto) {
        List<BookingInventory> bookingInventories = new ArrayList<>();
        LocalTime fromTime = bookingInventoryRequestDto.getFromTime();
        LocalTime toTime = bookingInventoryRequestDto.getToTime();
        LocalDate toDate = bookingInventoryRequestDto.getToDate();
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(LocalDate date = bookingInventoryRequestDto.getFromDate(); date.isBefore(toDate);date=date.plusDays(1)){
            for (LocalTime time=fromTime;time.isBefore(toTime);time= time.plusMinutes(10)){
                bookingInventories.add(BookingInventory.builder()
                                .date(date)
                                .time(time)
                                .bookingStatus(BookingStatus.AVAILABLE)
                                .doctorId(user.getId())
                                .build());
            }
        }

        bookingRepository.saveAll(bookingInventories);

    }


}
