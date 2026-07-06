package com.hospital.hospitalmanagementbackend.organization.specialization.dto.request;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSpecializationStatusRequest {

    @NotNull
    private Status status;
}
