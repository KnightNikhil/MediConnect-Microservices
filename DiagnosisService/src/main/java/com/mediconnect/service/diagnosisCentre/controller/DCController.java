package com.mediconnect.service.diagnosisCentre.controller;

import com.mediconnect.service.common_entities.dto.DiagnosisReportDto;
import com.mediconnect.service.diagnosisCentre.service.DiagnosisReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DCController {

    private final DiagnosisReportService diagnosisReportService;

    @PostMapping("/addDiagnosisReports")
    public ResponseEntity<Void> addDiagnosisReports(@RequestBody DiagnosisReportDto diagnosisReportDto) {
        diagnosisReportService.addDiagnosisReports(diagnosisReportDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/diagnosisReports/{recordId}")
    public List<DiagnosisReportDto> findDiagnosisReports(@PathVariable Long recordId){
        return diagnosisReportService.findDiagnosisReports(recordId);
    }

//    @PostMapping("addDCInventory")

}
