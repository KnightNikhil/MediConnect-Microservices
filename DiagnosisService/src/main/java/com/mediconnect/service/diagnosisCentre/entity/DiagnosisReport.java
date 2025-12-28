package com.mediconnect.service.diagnosisCentre.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ??? circular dependency warning
//    @ManyToOne
    @Column(name = "patient_consultation_record_id", nullable = false)
    private Long patientConsultationRecordId;

//    @ManyToOne
    @Column(name = "diagnosis_centre_id", nullable = false)
    private Long diagnosisCentreId;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String documentName;

    @Column(name = "document_url_s3", nullable = false)
    private String documentUrlS3;

}
