package com.hospital.hospitalmanagementbackend.organization.department.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class DepartmentDetailsResponse {

    private UUID id;

    private String name;

    private String description;

    private UUID headStaffId;

    private String headStaffName;

    private Status status;

    private LocalDateTime createdAt;
}
