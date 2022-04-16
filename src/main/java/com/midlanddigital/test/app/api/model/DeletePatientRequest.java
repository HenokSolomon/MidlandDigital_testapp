package com.midlanddigital.test.app.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePatientRequest {
    private String from;
    private String to;
}
