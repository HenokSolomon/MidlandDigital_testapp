package com.midlanddigital.test.app.domain.repository;

import com.midlanddigital.test.app.domain.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Staff findStaffByUuid(UUID uuid);
}
