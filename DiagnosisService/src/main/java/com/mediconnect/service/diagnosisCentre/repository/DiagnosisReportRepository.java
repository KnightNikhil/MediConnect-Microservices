package com.mediconnect.service.diagnosisCentre.repository;

import com.mediconnect.service.diagnosisCentre.entity.DiagnosisReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisReportRepository extends JpaRepository<DiagnosisReport, Long> {
    Page<DiagnosisReport> findAllByPatientConsultationRecordId(Long recordId, Pageable pageable);
}
