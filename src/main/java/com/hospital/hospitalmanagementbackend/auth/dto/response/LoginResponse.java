package com.hospital.hospitalmanagementbackend.auth.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String email;
    private String[] authorities;

    public LoginResponse(String token, String email, String[] authorities) {
        this.token = token;
        this.email = email;
        this.authorities = authorities;
    }
}
