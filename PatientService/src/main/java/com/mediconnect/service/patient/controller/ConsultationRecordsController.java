package com.mediconnect.service.patient.controller;

import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.common_entities.entity.PatientConsultationRecord;
import com.mediconnect.service.patient.service.PatientConsultationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsultationRecordsController {

    private final PatientConsultationRecordService recordsService;

    @GetMapping("/patientHistory/{patientId}")
//    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR')") --> does not work
//    @PreAuthorize("hasRole('PATIENT')")
    @PreAuthorize("hasAnyAuthority('ROLE_PATIENT','ROLE_DOCTOR')")
    public Page<PatientConsultationRecordDto> patientHistory(@PathVariable Long patientId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){
        // TODO: patient 101 should not be able to access patient 103 data

        return recordsService.findPatientHistory(patientId, page, size);
    }

    @GetMapping("/patientHistory/{recordId}/exists")
    public ResponseEntity<Boolean> validatedPatientConsultationRecord(@PathVariable Long recordId) {
        return ResponseEntity.ok(recordsService.validatePatientConsultationRecord(recordId));
    }
}
