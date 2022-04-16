package com.midlanddigital.test.app.domain.service;

import com.midlanddigital.test.app.domain.entity.Staff;
import com.midlanddigital.test.app.domain.exceptions.ServiceException;
import com.midlanddigital.test.app.domain.repository.StaffRepository;
import com.midlanddigital.test.app.dto.StaffDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static com.midlanddigital.test.app.Constants.dateFormat;
import static com.midlanddigital.test.app.Constants.dateTimeStringPattern;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {

        this.staffRepository = staffRepository;

        dateFormat.setTimeZone(TimeZone.getDefault());
    }

    @Override
    public StaffDto createStaff(String name) throws ServiceException {

        //validate name is not empty
        if (!StringUtils.hasText(name)) {
            throw new ServiceException("staff name is required ", ServiceException.STAFF_NAME_REQUIRED);
        }

        //prepare entity
        Staff staff = Staff.builder()
                .name(name)
                .uuid(UUID.randomUUID())
                .registrationDate(LocalDateTime.now())
                .build();

        //persist
        var newStaff = staffRepository.save(staff);

        return StaffDto.builder()
                .name(newStaff.getName())
                .uuid(newStaff.getUuid())
                .registrationDate(newStaff.getRegistrationDate().format(DateTimeFormatter.ofPattern(dateTimeStringPattern)))
                .build();

    }

    @Override
    public StaffDto updateStaff(UUID uuid, String name) throws ServiceException {

        //validate uuid is not empty
        if (uuid == null) {
            throw new ServiceException("staff uuid is required ", ServiceException.STAFF_UUID_REQUIRED);
        }

        //validate name is not empty
        if (!StringUtils.hasText(name)) {
            throw new ServiceException("staff name is required ", ServiceException.STAFF_NAME_REQUIRED);
        }

        Staff staff = validateStaff(uuid);

        //update field
        staff.setName(name);

        //persist
        var updatedStaff = staffRepository.save(staff);

        return StaffDto.builder()
                .name(updatedStaff.getName())
                .uuid(updatedStaff.getUuid())
                .registrationDate(updatedStaff.getRegistrationDate().format(DateTimeFormatter.ofPattern(dateTimeStringPattern)))
                .build();

    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff validateStaff(UUID uuid) {

        //validate staff
        Staff staff = staffRepository.findStaffByUuid(uuid);
        if (staff == null) {
            throw new ServiceException("staff with uuid " + uuid + " not found ", ServiceException.STAFF_INVALID);
        }

        return staff;
    }

}
