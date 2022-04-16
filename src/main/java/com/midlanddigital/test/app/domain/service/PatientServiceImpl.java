package com.midlanddigital.test.app.domain.service;


import com.midlanddigital.test.app.domain.entity.Patient;
import com.midlanddigital.test.app.domain.exceptions.ServiceException;
import com.midlanddigital.test.app.domain.repository.PatientRepository;
import com.midlanddigital.test.app.dto.PatientDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.midlanddigital.test.app.Constants.dateStringPattern;
import static com.midlanddigital.test.app.Constants.dateTimeStringPattern;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private static PatientDto mapToDto(Patient patient) {

        return PatientDto.builder()
                .patientId(patient.getPatientId())
                .name(patient.getName())
                .age(patient.getAge())
                .uuid(patient.getUuid())
                .lastVisitDate(patient.getLastVisitDate().format(DateTimeFormatter.ofPattern(dateTimeStringPattern)))
                .build();
    }

    @Override
    public List<PatientDto> getAllPatientByAgeLessThanEqual(int maxAge) {

        if (maxAge < 0) {
            throw new ServiceException("invalid date range ", ServiceException.DATE_RANGE_INVALID);
        }

        List<Patient> patientList = patientRepository.getAllByAgeLessThanEqual(maxAge);

        List<PatientDto> result = new ArrayList<>();

        if (patientList != null) {

            result = patientList.stream()
                    .map(PatientServiceImpl::mapToDto)
                    .collect(Collectors.toList());

        }

        return result;
    }

    @Override
    public PatientDto createPatient(PatientDto patient) {

        Patient newPatient = patientRepository.save(Patient.builder()
                .name(patient.getName())
                .uuid(UUID.randomUUID())
                .age(patient.getAge())
                .lastVisitDate(LocalDateTime.parse(patient.getLastVisitDate(), DateTimeFormatter.ofPattern(dateTimeStringPattern)))
                .build()
        );

        return mapToDto(newPatient);
    }

    @Override
    public PatientDto getPatientById(int id) {

        var patient = patientRepository.findById(id);

        return patient.map(PatientServiceImpl::mapToDto).orElse(null);
    }

    @Override
    public List<PatientDto> findAll() {

        return  patientRepository.findAll().stream()
                    .map(PatientServiceImpl::mapToDto)
                    .collect(Collectors.toList());
    }


    @Override
    public int delete(String from , String to) {

        if(!StringUtils.hasText(from) || !StringUtils.hasText(to)) {
            throw new ServiceException("please provide a valid date range", ServiceException.INVALID_DATE_RANGE);
        }

        LocalDateTime fromDate;
        LocalDateTime toDate;

        try {

            fromDate = LocalDateTime.of(LocalDate.parse(from, DateTimeFormatter.ofPattern(dateStringPattern)), LocalTime.MIN);
            toDate = LocalDateTime.of(LocalDate.parse(to, DateTimeFormatter.ofPattern(dateStringPattern)), LocalTime.MIN);

        } catch (DateTimeParseException parseException) {
            throw new ServiceException("unsupported date format , please use valid date format " + dateStringPattern, ServiceException.INVALID_DATE_FORMAT);
        }

        if(fromDate.isAfter(toDate) || fromDate.isEqual(toDate)) {
            throw new ServiceException("invalid date range, from_date should come before to_date" + dateStringPattern, ServiceException.INVALID_DATE_FORMAT);
        }

       return patientRepository.deletePatientByLastVisitDateBetween(fromDate, toDate);
    }

}
