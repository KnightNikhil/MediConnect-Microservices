package com.mediconnect.service.common_entities.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable // ??
@Getter
@Setter
public class MedicinePrescription {

    private String medicine;

    private Integer timesADay;

    private Boolean morning;

    private Boolean afternoon;

    private Boolean evening;

    private Boolean night;

    private Integer numberOfDays;

}
