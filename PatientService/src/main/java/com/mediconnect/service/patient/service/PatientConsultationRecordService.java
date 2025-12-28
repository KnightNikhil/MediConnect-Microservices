package com.mediconnect.service.patient.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportDto;
import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;

import java.util.List;

public interface PatientConsultationRecordService {

    List<PatientConsultationRecordDto> findPatientHistory(Long patientId);

}
