package com.mediconnect.service.patient.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportDto;
import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientConsultationRecordService {

    Page<PatientConsultationRecordDto> findPatientHistory(Long patientId, int page, int size);

}
