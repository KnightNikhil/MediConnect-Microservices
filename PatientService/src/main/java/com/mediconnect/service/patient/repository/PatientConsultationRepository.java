package com.mediconnect.service.patient.repository;

import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientConsultationRepository extends JpaRepository<PatientConsultationRecord, Long> {
    Page<PatientConsultationRecord> findAllByPatientId(Long patientId, Pageable pageable);
}
