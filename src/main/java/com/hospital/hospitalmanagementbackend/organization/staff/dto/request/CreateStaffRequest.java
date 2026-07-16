package com.hospital.hospitalmanagementbackend.organization.staff.dto.request;

import com.hospital.hospitalmanagementbackend.common.enums.EmploymentType;
import com.hospital.hospitalmanagementbackend.common.enums.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CreateStaffRequest {

    @NotNull(message = ValidationMessages.REQUIRED)
    private UUID userId;

    @NotNull(message = ValidationMessages.REQUIRED)
    private UUID departmentId;

    @NotNull(message = ValidationMessages.REQUIRED)
    private UUID designationId;

    private UUID reportingManagerId;

    @NotNull(message = ValidationMessages.REQUIRED)
    private LocalDate joiningDate;

    @NotNull(message = ValidationMessages.REQUIRED)
    private EmploymentType employmentType;

    @PositiveOrZero(message = ValidationMessages.POSITIVE_NUMBER)
    private BigDecimal salary;
}
