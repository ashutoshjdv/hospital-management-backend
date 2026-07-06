package com.hospital.hospitalmanagementbackend.organization.department.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateDepartmentRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    private UUID headStaffId;
}
