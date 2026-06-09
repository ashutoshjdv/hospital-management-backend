package com.hospital.hospitalmanagementbackend.auth.dto.response;

import com.hospital.hospitalmanagementbackend.auth.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String email;
    private String status;
    private boolean isEmailVerified;
    private List<UserRoleResponse> roles;
}
