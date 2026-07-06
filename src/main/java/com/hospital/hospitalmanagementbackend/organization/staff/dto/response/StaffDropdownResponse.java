package com.hospital.hospitalmanagementbackend.organization.staff.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class StaffDropdownResponse {

    private UUID id;

    private String employeeCode;

    private String employeeName;
}
