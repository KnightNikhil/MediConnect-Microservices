package com.mediconnect.service.diagnosisCentre.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportDto;
import com.mediconnect.service.diagnosisCentre.entity.DiagnosisReport;
import com.mediconnect.service.diagnosisCentre.repository.DiagnosisReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisReportServiceImpl implements DiagnosisReportService {

    private final DiagnosisReportRepository diagnosisReportRepository;

    public void addDiagnosisReports(DiagnosisReportDto diagnosisReportDto) {
        DiagnosisReport report = DiagnosisReport.builder()
                .patientConsultationRecordId(diagnosisReportDto.getPatientConsultationRecordId())
                .diagnosisCentreId(1L) // curremt leegin in user // ???
                .dateTime(LocalDateTime.now())
                .documentName(diagnosisReportDto.getDocumentName())
                .documentUrlS3("diagnosisReportDto.getDocumentUrlS3()") // from S3 upload // ???
                .build();
        diagnosisReportRepository.save(report);
    }

    @Override
    public List<DiagnosisReportDto> findDiagnosisReports(Long recordId) {
        List<DiagnosisReport> allDiagnosisReportsByRecordId = diagnosisReportRepository.findAllByPatientConsultationRecordId(recordId);
        List<DiagnosisReportDto> diagnosisReportDtoList = new ArrayList<>();
        for(DiagnosisReport report : allDiagnosisReportsByRecordId){
            diagnosisReportDtoList.add(DiagnosisReportDto.builder()
                    .id(Long.valueOf(report.getId()))
                    .patientConsultationRecordId(report.getPatientConsultationRecordId())
//                    .diagnosisCentre(report.getDiagnosisCentreId())
                    .dateTime(report.getDateTime())
                    .documentUrlS3(report.getDocumentUrlS3())
                    .documentName(report.getDocumentName())
                    .build());
        }
        return diagnosisReportDtoList;
    }
}
