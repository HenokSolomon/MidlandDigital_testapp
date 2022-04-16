package com.midlanddigital.test.app.domain.repository;

import com.midlanddigital.test.app.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> getAllByAgeLessThanEqual(int age);

    @Transactional
    int deletePatientByLastVisitDateBetween(LocalDateTime from, LocalDateTime to);
}
