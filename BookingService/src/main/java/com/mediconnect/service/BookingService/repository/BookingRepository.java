package com.mediconnect.service.BookingService.repository;

import com.mediconnect.service.common_entities.entity.BookingInventory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingInventory, Long> {

//    @Lock(LockModeType.OPTIMISTIC)
//    void saveAll(List<BookingInventory> bookingInventories);

    BookingInventory findByDoctorIdAndDateAndTime(Long doctorId, LocalDate date, LocalTime time);


}
