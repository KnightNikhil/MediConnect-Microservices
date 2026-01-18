package com.mediconnect.service.patient.service;

import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import org.springframework.data.domain.Page;

public interface PatientConsultationRecordService {

    Page<PatientConsultationRecordDto> findPatientHistory(Long patientId, int page, int size);

    Boolean validatePatientConsultationRecord(Long recordId);

}
