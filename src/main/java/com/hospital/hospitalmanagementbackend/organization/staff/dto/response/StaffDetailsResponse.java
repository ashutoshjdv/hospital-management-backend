package com.hospital.hospitalmanagementbackend.organization.staff.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.EmploymentType;
import com.hospital.hospitalmanagementbackend.common.enums.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class StaffDetailsResponse {

    private UUID id;

    private UUID userId;

    private String employeeCode;

    private String employeeName;

    private String email;

    private UUID departmentId;

    private String departmentName;

    private UUID designationId;

    private String designationName;

    private UUID reportingManagerId;

    private String reportingManagerName;

    private LocalDate joiningDate;

    private EmploymentType employmentType;

    private BigDecimal salary;

    private StaffStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
