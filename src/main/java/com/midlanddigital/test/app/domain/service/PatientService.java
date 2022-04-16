package com.midlanddigital.test.app.domain.service;

import com.midlanddigital.test.app.dto.PatientDto;

import java.util.List;

public interface PatientService {

    /**
     * returns patient who's age is less or equal @param maxAge
     *
     * @param maxAge
     * @return list of patient
     */
    List<PatientDto> getAllPatientByAgeLessThanEqual(int maxAge);

    /**
     * get patient by id
     *
     * @param id
     * @return
     */
    PatientDto getPatientById(int id);

    /**
     * @param patient
     * @return
     */
    PatientDto createPatient(PatientDto patient);

    /**
     *
     * @return
     */
    List<PatientDto> findAll();

    /**
     *
     * @param from
     * @param to
     * @return
     */
    int delete(String from , String to);
}
