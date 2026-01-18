package com.mediconnect.service.common_entities.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DiagnosisReportRequestDto {
    private Long patientConsultationRecordId;
    private Long diagnosisCentreId;
    private String documentName;
    private String documentUrlS3;

}
