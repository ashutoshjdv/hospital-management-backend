package com.hospital.hospitalmanagementbackend.organization.staff.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class StaffListResponse {

    private UUID id;

    private String employeeCode;

    private String employeeName;

    private String designation;

    private String department;

    private StaffStatus status;
}
