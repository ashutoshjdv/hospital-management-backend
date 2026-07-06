package com.hospital.hospitalmanagementbackend.organization.designation.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.DesignationCategory;
import com.hospital.hospitalmanagementbackend.common.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DesignationListResponse {

    private UUID id;

    private String name;

    private Integer level;

    private DesignationCategory category;

    private Status status;
}
