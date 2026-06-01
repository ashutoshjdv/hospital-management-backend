package com.hospital.hospitalmanagementbackend.auth.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    public LoginResponse(String message) {
        this.token = message;
    }
}
