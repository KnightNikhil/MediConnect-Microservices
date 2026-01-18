package com.mediconnect.service.diagnosisCentre.controller;

import com.mediconnect.service.common_entities.dto.DiagnosisReportRequestDto;
import com.mediconnect.service.diagnosisCentre.service.DiagnosisReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DCController {

    private final DiagnosisReportService diagnosisReportService;

    @PostMapping("/addDiagnosisReports")
    public ResponseEntity<Void> addDiagnosisReports(@RequestBody DiagnosisReportRequestDto diagnosisReportRequestDto) {
        diagnosisReportService.addDiagnosisReports(diagnosisReportRequestDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/diagnosisReports/{recordId}")
    public ResponseEntity<Page<DiagnosisReportRequestDto>> findDiagnosisReports(@PathVariable Long recordId, @RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok(diagnosisReportService.findDiagnosisReports(recordId, pageNumber, pageSize));
    }

//    @PostMapping("addDCInventory")

}
