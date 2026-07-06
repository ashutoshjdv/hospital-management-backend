package com.hospital.hospitalmanagementbackend.organization.specialization.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSpecializationRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;
}
