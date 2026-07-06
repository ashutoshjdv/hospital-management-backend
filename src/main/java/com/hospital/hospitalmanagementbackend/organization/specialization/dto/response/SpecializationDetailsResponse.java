package com.hospital.hospitalmanagementbackend.organization.specialization.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class SpecializationDetailsResponse {

    private UUID id;

    private String name;

    private String description;

    private Status status;

    private LocalDateTime createdAt;
}
