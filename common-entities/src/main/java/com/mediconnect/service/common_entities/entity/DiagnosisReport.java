package com.mediconnect.service.common_entities.entity;


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
    @ManyToOne(fetch = FetchType.LAZY) // Many DiagnosisReport rows can have same(one) patientConsultationRecordId
    @JoinColumn(name = "patient_consultation_record_id", nullable = false)
    private PatientConsultationRecord patientConsultationRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis_centre_id", nullable = false)
    private DiagnosisCentre diagnosisCentre;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String documentName;

    @Column(name = "document_url_s3", nullable = false)
    private String documentUrlS3;

}
