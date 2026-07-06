package com.hospital.hospitalmanagementbackend.organization.doctorprofile.dto.request;

import com.hospital.hospitalmanagementbackend.common.enums.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDoctorProfileRequest {

    @NotBlank(message = ValidationMessages.REQUIRED)
    @Size(max = 100)
    private String licenseNumber;

    @Size(max = 255)
    private String qualification;

    @PositiveOrZero(message = ValidationMessages.POSITIVE_NUMBER)
    private Integer experienceYears;

    @PositiveOrZero(message = ValidationMessages.POSITIVE_NUMBER)
    private BigDecimal consultationFee;

    private String bio;

    @NotEmpty(message = ValidationMessages.REQUIRED)
    private Set<UUID> specializationIds;
}
