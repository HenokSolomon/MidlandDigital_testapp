package com.midlanddigital.test.app.api;

import com.midlanddigital.test.app.domain.service.PatientService;
import com.midlanddigital.test.app.domain.service.StaffService;
import com.midlanddigital.test.app.dto.PatientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping(value = {"/patient"})
@RestController
@Slf4j
public class PatientRestController {

    private static final Integer AGE_QUERY = 2;

    private final PatientService patientService;
    private final StaffService staffService;

    public PatientRestController(PatientService patientService, StaffService staffService) {
        this.patientService = patientService;
        this.staffService = staffService;
    }

    @ValidateStaffUUID /* custom annotation to intercept staffUUID httpHeader */
    @GetMapping("/getPatientsAgedLessThanTwoYear")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public List<PatientDto> getPatientsAgedLessThanTwoYear() {

        return patientService.getAllPatientByAgeLessThanEqual(AGE_QUERY);
    }


    @ValidateStaffUUID /* custom annotation to intercept staffUUID httpHeader */
    @GetMapping("/downloadPatientProfile?{from}&{to}")
    public void downloadPatientProfile(HttpServletResponse response, @PathVariable String from, @PathVariable String to) throws IOException {

        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Patients_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        //fixme
        List<PatientDto> patients = patientService.getAllPatientByAgeLessThanEqual(AGE_QUERY);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"UUID", "Name", "age", "Last Visit Date"};
        String[] nameMapping = {"uuid", "name", "age", "lastVisitDate"};

        csvWriter.writeHeader(csvHeader);

        for (PatientDto p : patients) {
            csvWriter.write(p, nameMapping);
        }

        csvWriter.close();

    }

    @ValidateStaffUUID /* custom annotation to intercept staffUUID httpHeader */
    @DeleteMapping("/delete?{from}&{to}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void delete(HttpServletResponse response, @PathVariable String from, @PathVariable String to) throws IOException {


    }

}
