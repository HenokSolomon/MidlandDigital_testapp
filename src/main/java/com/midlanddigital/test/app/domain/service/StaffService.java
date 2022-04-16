package com.midlanddigital.test.app.domain.service;

import com.midlanddigital.test.app.domain.entity.Staff;
import com.midlanddigital.test.app.domain.exceptions.ServiceException;
import com.midlanddigital.test.app.dto.StaffDto;

import java.util.List;
import java.util.UUID;

public interface StaffService {

    /**
     * creates staff record
     *
     * @param name
     * @return
     * @throws ServiceException
     */
    StaffDto createStaff(String name) throws ServiceException;


    /**
     * updates staff
     *
     * @param uuid
     * @param name
     * @return
     * @throws ServiceException
     */
    StaffDto updateStaff(UUID uuid, String name) throws ServiceException;

    /**
     * check if uuid belongs to staff if ok then it returns the staff else throw error
     *
     * @param uuid
     * @return
     */
    Staff validateStaff(UUID uuid);

    List<Staff> findAll();

}
