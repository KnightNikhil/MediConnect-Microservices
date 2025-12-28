package com.mediconnect.service.doctor.repository;

import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<PatientConsultationRecord, Long> {
}
