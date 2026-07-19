package com.hospital.hospitalmanagementbackend.organization.doctorprofile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class DoctorProfileResponse {


    private UUID staffId;

    private String employeeName;

    private String licenseNumber;

    private String qualification;

    private Integer experienceYears;

    private BigDecimal consultationFee;

    private String bio;

    private List<String> specializations;
}