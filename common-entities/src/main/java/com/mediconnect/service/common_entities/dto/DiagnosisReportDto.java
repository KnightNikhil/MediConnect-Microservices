package com.mediconnect.service.common_entities.dto;


import com.mediconnect.service.common_entities.entity.DiagnosisCentre;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class DiagnosisReportDto {
    private Long id;
    private Long patientConsultationRecordId;
    private DiagnosisCentre diagnosisCentre;
    private LocalDateTime dateTime;
    private String documentName;
    private String documentUrlS3;

}
