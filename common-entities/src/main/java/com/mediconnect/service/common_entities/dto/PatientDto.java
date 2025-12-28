package com.mediconnect.service.common_entities.dto;

import com.mediconnect.service.common_entities.entity.Enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {

    private LocalDate dateOfBirth;
    private Gender gender;

}
