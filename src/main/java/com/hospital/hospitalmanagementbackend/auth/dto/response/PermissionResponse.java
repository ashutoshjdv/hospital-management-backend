package com.hospital.hospitalmanagementbackend.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {

    private UUID id;
    private String name;
    private String description;


}
