package com.hospital.hospitalmanagementbackend.organization.designation.dto.request;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDesignationStatusRequest {

    @NotNull
    private Status status;
}
