package com.midlanddigital.test.app.api;

import com.midlanddigital.test.app.api.model.StaffRequest;
import com.midlanddigital.test.app.domain.entity.Staff;
import com.midlanddigital.test.app.domain.service.StaffService;
import com.midlanddigital.test.app.dto.StaffDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(value = {"/staff"})
@RestController
public class StaffRestController {

    private final StaffService staffService;

    public StaffRestController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/create")
    @ResponseBody
    public StaffDto create(@RequestBody StaffRequest request) {

        return staffService.createStaff(request.getName());
    }

    @PutMapping("update/{uuid}")
    public StaffDto update(@PathVariable UUID uuid, @RequestBody StaffRequest request) {

        return staffService.updateStaff(uuid, request.getName());
    }

    @GetMapping("/findAll")
    public List<Staff> findAll() {

        return staffService.findAll();
    }

}
