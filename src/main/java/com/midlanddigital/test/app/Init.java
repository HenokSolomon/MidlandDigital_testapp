package com.midlanddigital.test.app;

import com.midlanddigital.test.app.domain.service.PatientService;
import com.midlanddigital.test.app.domain.service.StaffService;
import com.midlanddigital.test.app.dto.PatientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class Init {

    private static final String dateTimeStringPattern = "yyyy-MM-dd HH:mm";
    private final StaffService staffService;
    private final PatientService patientService;


    public Init(StaffService staffService, PatientService patientService) {
        this.staffService = staffService;
        this.patientService = patientService;
    }

    /*executes 2 sec after the container starts ,and prepares some test mock data */
    @Scheduled(initialDelay = 1000 * 2, fixedDelay = Long.MAX_VALUE)
    public void init() {

        log.info("Init : started init app mock data ");

        for (int i = 1; i <= 10; i++) {
            staffService.createStaff("Test Staff _ " + i);
        }

        log.info("Init : completed init of staff mock data ");

        for (int i = 1; i <= 10; i++) {

            patientService.createPatient(PatientDto.builder()
                    .name("Test Patient " + i)
                    .lastVisitDate(LocalDateTime.now().minusDays(i).format(DateTimeFormatter.ofPattern(dateTimeStringPattern)))
                    .age(i)
                    .build()
            );
        }

        log.info("Init : completed init of patient mock data ");

    }

}
