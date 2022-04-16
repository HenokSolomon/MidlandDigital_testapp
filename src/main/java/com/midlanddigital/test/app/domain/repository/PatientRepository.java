package com.midlanddigital.test.app.domain.repository;

import com.midlanddigital.test.app.domain.entity.Patient;
import com.midlanddigital.test.app.domain.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> getAllByAgeLessThanEqual(int age);
}
