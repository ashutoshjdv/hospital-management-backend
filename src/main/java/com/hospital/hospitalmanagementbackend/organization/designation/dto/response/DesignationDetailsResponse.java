package com.hospital.hospitalmanagementbackend.organization.designation.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.DesignationCategory;
import com.hospital.hospitalmanagementbackend.common.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class DesignationDetailsResponse {

    private UUID id;

    private String name;

    private String description;

    private Integer level;

    private DesignationCategory category;

    private Status status;

    private LocalDateTime createdAt;
}
