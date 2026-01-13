package com.mediconnect.service.common_entities.entity;

import com.mediconnect.service.common_entities.entity.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)// Many bookings -> One Doctor(foreign key here)
    @JoinColumn( name = "doctor_id", nullable = false) // name in BookingInventory schema -> has nothing to do with Patient schema, that relationship is already established in @ManyToOne
    // though it tells the current schema that doctor_id can not be null, so there must exist a doctor for this booking
    // Use @JoinColumn on the owning side of a relationship. since Doctor is foreign key here, bookings is the owning side
    private Doctor doctor;

//    private Long diagnosisCentreId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne(fetch = FetchType.LAZY) // Many bookings -> One patient(foreign key here)
    @JoinColumn(name = "patient_id", nullable = false) // name in BookingInventory schema -> has nothing to do with patient schema, that relationship is already established in @ManyToOne
    // though it tells the current schema that booked_for_patient_id can not be null, so there must exist a doctor for this booking
    private Patient patientId;


}
