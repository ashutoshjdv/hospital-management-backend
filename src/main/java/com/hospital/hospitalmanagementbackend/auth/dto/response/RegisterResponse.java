package com.hospital.hospitalmanagementbackend.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RegisterResponse {

    private UUID userId;

    private String email;

    private String message;

}
