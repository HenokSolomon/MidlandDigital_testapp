package com.midlanddigital.test.app.domain.service;

import com.midlanddigital.test.app.dto.PatientDto;

import java.util.List;

public interface PatientService {

    /**
     * returns patient who's age is less or equal @param maxAge
     * @param maxAge
     * @return list of patient
     */
    List<PatientDto> getAllPatientByAgeLessThanEqual(int maxAge);
}
