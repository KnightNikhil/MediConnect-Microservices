package com.mediconnect.service.common_entities.dto;

import com.mediconnect.service.common_entities.entity.MedicinePrescription;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;

    @NotNull
    private Integer age;

    @Positive
    private Double weight;

    @NotBlank
    private String symptoms;

    @NotBlank
    private String clinicalDiagnosis;
    private LocalDateTime consultancyDateTime;
    private List<Long> diagnosisReportsId;
    private List<MedicinePrescription> medicinePrescriptionList;

}
