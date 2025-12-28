package com.mediconnect.service.common_entities.dto;

import com.mediconnect.service.common_entities.entity.Enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@Data
public class DoctorDto {

    private LocalDate dateOfBirth;
    private Gender gender;
    private String specialization;
    private List<String> degrees;

}
