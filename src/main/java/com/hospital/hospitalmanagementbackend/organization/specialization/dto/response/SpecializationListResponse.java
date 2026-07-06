package com.hospital.hospitalmanagementbackend.organization.specialization.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class SpecializationListResponse {

    private UUID id;

    private String name;

    private Status status;
}
