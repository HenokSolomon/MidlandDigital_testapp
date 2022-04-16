package com.midlanddigital.test.app.api;

import com.midlanddigital.test.app.api.model.StaffRequest;
import com.midlanddigital.test.app.domain.service.StaffService;
import com.midlanddigital.test.app.dto.StaffDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{uuid}")
    public StaffDto update(@PathVariable UUID uuid, @RequestBody StaffRequest request) {

        return staffService.updateStaff(uuid, request.getName());
    }

}
