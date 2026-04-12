package com.mediconnect.service.patient.service;

import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import org.springframework.data.domain.Page;

public interface PatientConsultationRecordService {

    Page<PatientConsultationRecordDto> findPatientHistory(Long patientId, int page, int size);

    PatientConsultationRecord validatePatientConsultationRecord(Long recordId);

}
