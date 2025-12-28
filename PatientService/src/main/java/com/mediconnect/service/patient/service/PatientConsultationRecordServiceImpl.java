package com.mediconnect.service.patient.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportDto;
import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import com.mediconnect.service.patient.repository.PatientConsultationRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientConsultationRecordServiceImpl implements PatientConsultationRecordService {

    private final PatientConsultationRepository patientConsultationRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PatientConsultationRecordDto> findPatientHistory(Long patientId) {
        List<PatientConsultationRecord> allRecordsByPatientId = patientConsultationRepository.findAllByPatientId(patientId);
        List<PatientConsultationRecordDto> consultationRecordList = new ArrayList<>();
        for (PatientConsultationRecord recordByPatientId : allRecordsByPatientId) {
            consultationRecordList.add(modelMapper.map(recordByPatientId, PatientConsultationRecordDto.class));
    }
        return consultationRecordList;
    }


}
