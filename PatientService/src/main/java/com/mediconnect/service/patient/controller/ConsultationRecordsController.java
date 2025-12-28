package com.mediconnect.service.patient.controller;

import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.patient.service.PatientConsultationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsultationRecordsController {

    private final PatientConsultationRecordService recordsService;

    @GetMapping("/patientHistory/{patientId}")
    @PreAuthorize("hasAuthority('PATIENT', 'DOCTOR')")
    public List<PatientConsultationRecordDto> patientHistory(@PathVariable Long patientId){
        return recordsService.findPatientHistory(patientId);
    }

}
