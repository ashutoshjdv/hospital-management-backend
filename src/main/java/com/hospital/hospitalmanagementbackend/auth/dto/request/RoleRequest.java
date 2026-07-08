package com.hospital.hospitalmanagementbackend.auth.dto.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RoleRequest {

    private String name;

    private String description;

    private List<UUID> permissionIds;
}
