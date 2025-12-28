package com.mediconnect.service.common_entities.dto;

import com.mediconnect.service.common_entities.entity.MedicinePrescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientConsultationRecordDto {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime consultancyDateTime;
    private Integer age;
    private Double weight;
    private String symptoms;
    private String clinicalDiagnosis;
    private List<Long> diagnosisReportsId;
    private List<MedicinePrescription> medicinePrescriptionList;

}
