package com.midlanddigital.test.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private Integer patientId;
    private String name;
    private UUID uuid;
    private int age;
    private String lastVisitDate;

}
