package com.mediconnect.service.diagnosisCentre.service;

import com.mediconnect.service.common_entities.dto.DiagnosisReportRequestDto;
import com.mediconnect.service.common_entities.entity.DiagnosisCentre;
import com.mediconnect.service.common_entities.exception.InvalidCredentialsException;
import com.mediconnect.service.diagnosisCentre.entity.DiagnosisReport;
import com.mediconnect.service.diagnosisCentre.repository.DiagnosisReportRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class DiagnosisReportServiceImpl implements DiagnosisReportService {

    private final DiagnosisReportRepository diagnosisReportRepository;

    private final PatientHistoryClient patientHistoryClient;

    private final ModelMapper modelMapper;

    @Override
    public void addDiagnosisReports(DiagnosisReportRequestDto diagnosisReportRequestDto) {

        DiagnosisCentre user = (DiagnosisCentre) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean patientConsultationRecordExists = patientHistoryClient.validatePatientConsultationRecord(diagnosisReportRequestDto.getPatientConsultationRecordId());

        if(!patientConsultationRecordExists)
            throw new InvalidCredentialsException("Patient Consultation Record does not exist with record id " +
                    diagnosisReportRequestDto.getPatientConsultationRecordId(), HttpStatus.NOT_ACCEPTABLE);


        DiagnosisReport report = DiagnosisReport.builder()
                .patientConsultationRecordId(diagnosisReportRequestDto.getPatientConsultationRecordId())
                .diagnosisCentre(user)
                .dateTime(LocalDateTime.now())
                .documentName(diagnosisReportRequestDto.getDocumentName())
                .documentUrlS3("diagnosisReportDto.getDocumentUrlS3()") // from S3 upload // ???
                .build();
        diagnosisReportRepository.save(report);

    }

    @Transactional(readOnly = true)
    @Override
    public Page<DiagnosisReportRequestDto> findDiagnosisReports(Long recordId, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<DiagnosisReport> allDiagnosisReportsByRecordId = diagnosisReportRepository.findAllByPatientConsultationRecordId(recordId, pageable);

        //        List<DiagnosisReportRequestDto> diagnosisReportRequestDtoList = new ArrayList<>();

//        for(DiagnosisReport report : allDiagnosisReportsByRecordId){
//            diagnosisReportRequestDtoList.add(DiagnosisReportRequestDto.builder()
//                    .patientConsultationRecordId(recordId)
//                    .diagnosisCentre(report.getDiagnosisCentreId())
//                    .dateTime(report.getDateTime())
//                    .documentUrlS3(report.getDocumentUrlS3())
//                    .documentName(report.getDocumentName())
//                    .build());
//        }

    return allDiagnosisReportsByRecordId.map(
            report -> modelMapper.map(report, DiagnosisReportRequestDto.class));

    }
}
