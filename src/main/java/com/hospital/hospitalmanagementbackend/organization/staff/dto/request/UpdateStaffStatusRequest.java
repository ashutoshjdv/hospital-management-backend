package com.hospital.hospitalmanagementbackend.organization.staff.dto.request;

import com.hospital.hospitalmanagementbackend.common.enums.StaffStatus;
import com.hospital.hospitalmanagementbackend.common.enums.ValidationMessages;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateStaffStatusRequest {

    @NotNull(message = ValidationMessages.REQUIRED)
    private StaffStatus status;
}