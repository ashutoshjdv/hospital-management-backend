package com.hospital.hospitalmanagementbackend.organization.designation.dto.request;

import com.hospital.hospitalmanagementbackend.common.enums.DesignationCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDesignationRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    @NotNull
    @Positive
    private Integer level;

    @NotNull
    private DesignationCategory category;
}
