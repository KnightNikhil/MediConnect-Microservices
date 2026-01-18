package com.mediconnect.service.diagnosisCentre.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportRequestDto;
import org.springframework.data.domain.Page;

public interface DiagnosisReportService {

    public void addDiagnosisReports(DiagnosisReportRequestDto diagnosisReportRequestDto);

    Page<DiagnosisReportRequestDto> findDiagnosisReports(Long recordId, int pageNumber, int pageSize);
}
