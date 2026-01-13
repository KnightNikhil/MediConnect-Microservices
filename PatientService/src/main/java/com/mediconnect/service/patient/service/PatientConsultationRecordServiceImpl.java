package com.mediconnect.service.patient.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportDto;
import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import com.mediconnect.service.common_entities.exception.DBException;
import com.mediconnect.service.common_entities.exception.DataUnavailable;
import com.mediconnect.service.patient.repository.PatientConsultationRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientConsultationRecordServiceImpl implements PatientConsultationRecordService {

    private final PatientConsultationRepository patientConsultationRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<PatientConsultationRecordDto> findPatientHistory(Long patientId, int page, int size) {
        Page<PatientConsultationRecord> records; // dont use List<PatientConsultationRecord> instead Page
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "consultancyDateTime"));
            records = patientConsultationRepository.findAllByPatientId(patientId, pageable); // Paginated queries should NOT return Optional,	Empty page ≠ error
        } catch (Exception e) {
            throw new DBException("Could not fetch Patient Consultation Record for Patient Id: " + patientId + " from DB");
        }

        Page<PatientConsultationRecordDto> patientConsultationRecordDtoPage = records.map( record ->
                modelMapper.map(record, PatientConsultationRecordDto.class)

        );

        // ==> below code loses pageable metadata
        //        List<PatientConsultationRecordDto> consultationRecordList = new ArrayList<>();
        //        for (PatientConsultationRecord recordByPatientId : allRecordsByPatientId) {
        //            consultationRecordList.add(modelMapper.map(recordByPatientId, PatientConsultationRecordDto.class));
        //    }
        return patientConsultationRecordDtoPage;
    }


}
