package com.mediconnect.service.doctor.controller;

import com.mediconnect.service.common_entities.dto.PatientConsultationRecordDto;
import com.mediconnect.service.doctor.service.ConsultationRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final ConsultationRecordService consultationRecordService;

    @PostMapping("/addConsultationRecord")
    public void addConsultationRecord(@RequestBody @Valid  PatientConsultationRecordDto patientConsultationRecordDto){
        consultationRecordService.addConsultationRecord(patientConsultationRecordDto);
    }

}
