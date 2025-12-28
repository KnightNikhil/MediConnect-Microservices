package com.mediconnect.service.patient.repository;

import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientConsultationRepository extends JpaRepository<PatientConsultationRecord, Long> {
    List<PatientConsultationRecord> findAllByPatientId(Long patientId);
}
