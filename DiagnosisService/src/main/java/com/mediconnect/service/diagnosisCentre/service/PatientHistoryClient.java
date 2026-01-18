package com.mediconnect.service.diagnosisCentre.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "patient-service",
        path = "/patientHistory"
)
public interface PatientHistoryClient {

    @GetMapping("{recordId}/exists")
    Boolean validatePatientConsultationRecord(@PathVariable Long recordId);

}
