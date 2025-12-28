package com.mediconnect.service.doctor.service;

import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.doctor.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ConsultationRecordServiceImpl implements ConsultationRecordService {

    private final ConsultationRepository consultationRepository;

    @Override
    public void addConsultationRecord(PatientConsultationRecordDto patientConsultationRecordDto) {
        consultationRepository.save(
                PatientConsultationRecord.builder()
                        .patientId(patientConsultationRecordDto.getPatientId())
                        .doctorId(patientConsultationRecordDto.getDoctorId())
                        .consultancyDateTime(LocalDateTime.now())
                        .age(1)
                        .weight(patientConsultationRecordDto.getWeight())
                        .symptoms(patientConsultationRecordDto.getSymptoms())
                        .clinicalDiagnosis(patientConsultationRecordDto.getClinicalDiagnosis())
                        .diagnosisReportsId(new ArrayList<>())
                        .medicinePrescriptionList(patientConsultationRecordDto.getMedicinePrescriptionList())
                        .build()
        );
    }
}
