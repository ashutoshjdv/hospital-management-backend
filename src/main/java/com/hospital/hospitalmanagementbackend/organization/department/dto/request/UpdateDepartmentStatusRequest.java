package com.hospital.hospitalmanagementbackend.organization.department.dto.request;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDepartmentStatusRequest {

    @NotNull
    private Status status;
}
