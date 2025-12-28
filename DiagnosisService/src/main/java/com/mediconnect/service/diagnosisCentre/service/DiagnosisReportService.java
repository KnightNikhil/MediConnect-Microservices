package com.mediconnect.service.diagnosisCentre.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportDto;

import java.util.List;

public interface DiagnosisReportService {

    public void addDiagnosisReports(DiagnosisReportDto diagnosisReportDto);

    List<DiagnosisReportDto> findDiagnosisReports(Long recordId);
}
