package com.midlanddigital.test.app.domain.service;


import com.midlanddigital.test.app.domain.entity.Patient;
import com.midlanddigital.test.app.domain.exceptions.ServiceException;
import com.midlanddigital.test.app.domain.repository.PatientRepository;
import com.midlanddigital.test.app.dto.PatientDto;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientDto> getAllPatientByAgeLessThanEqual(int maxAge) {

        if(maxAge < 0) {
            throw new ServiceException("invalid date range ", ServiceException.DATE_RANGE_INVALID);
        }

        List<Patient> patientList = patientRepository.getAllByAgeLessThanEqual(maxAge);

        List<PatientDto> result = new ArrayList<>();

        if(patientList != null) {

            result = patientList.stream()
                    .map(PatientServiceImpl::mapToDto)
                    .collect(Collectors.toList());

        }

        return result;
    }

    private static PatientDto mapToDto(Patient patient) {

        return PatientDto.builder()
                .name(patient.getName())
                .age(patient.getAge())
                .uuid(patient.getUuid())
                .lastVisitDate(dateFormat.format(patient.getLastVisitDate()))
                .build();
    }


}
