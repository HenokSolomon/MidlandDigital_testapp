package com.midlanddigital.test.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDetailsDto {

    private UUID TestAppRecordId;
    private String name;
    private String email;
    private LocalDateTime invitationSentDate;
    private LocalDateTime invitationConfirmDate;
    private String invitationConfirmRemark;
    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;
}
