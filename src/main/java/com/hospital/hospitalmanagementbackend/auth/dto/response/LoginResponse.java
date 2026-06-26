package com.hospital.hospitalmanagementbackend.auth.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String[] authorities;

    public LoginResponse(String accessToken, String refreshToken, String email, String[] authorities) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.email = email;
        this.authorities = authorities;
    }
}
