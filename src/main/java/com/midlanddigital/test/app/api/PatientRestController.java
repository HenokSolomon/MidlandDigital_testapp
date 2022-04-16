package com.midlanddigital.test.app.api;

import com.midlanddigital.test.app.api.model.DeletePatientRequest;
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

    @ValidateStaffUUID
    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PatientDto> findAll() {

        return patientService.findAll();
    }

    @ValidateStaffUUID /* custom annotation to intercept staffUUID httpHeader */
    @GetMapping("/getPatientsAgedLessThanTwoYear")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PatientDto> getPatientsAgedLessThanTwoYear() {

        return patientService.getAllPatientByAgeLessThanEqual(AGE_QUERY);
    }


    @ValidateStaffUUID /* custom annotation to intercept staffUUID httpHeader */
    @GetMapping("/downloadPatientProfile/{id}")
    public void downloadPatientProfile(HttpServletResponse response, @PathVariable int id) throws IOException {

        PatientDto patient = patientService.getPatientById(id);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Patients_" + id + ".csv";

        response.setHeader(headerKey, headerValue);
        response.setContentType("text/csv");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"UUID", "Name", "age", "Last Visit Date"};
        String[] nameMapping = {"uuid", "name", "age", "lastVisitDate"};

        csvWriter.writeHeader(csvHeader);
        csvWriter.write(patient == null ? new PatientDto() : patient, nameMapping);

        csvWriter.close();

    }

    @ValidateStaffUUID /* custom annotation to intercept staffUUID httpHeader */
    @DeleteMapping("/delete")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String delete(@RequestBody DeletePatientRequest deletePatientRequest) {

        final int count = patientService.delete(deletePatientRequest.getFrom(), deletePatientRequest.getTo());

        return "total number of deleted records " + count;
    }

}
