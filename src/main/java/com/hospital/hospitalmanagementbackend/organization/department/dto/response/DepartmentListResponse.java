package com.hospital.hospitalmanagementbackend.organization.department.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DepartmentListResponse {

    private UUID id;

    private String name;

    private String headName;

    private Status status;
}