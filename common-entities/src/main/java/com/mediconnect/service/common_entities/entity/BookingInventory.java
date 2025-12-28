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

//    @ManyToOne
//    @JoinColumn( name = "doctor_id", nullable = false)
    private Long doctorId;

//    private Long diagnosisCentreId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

//    @ManyToOne
//    @JoinColumn(name = "booked_for_patient_id")
    private Long patientId;


}
