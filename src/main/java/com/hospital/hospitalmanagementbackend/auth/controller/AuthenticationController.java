package com.hospital.hospitalmanagementbackend.auth.controller;

import com.hospital.hospitalmanagementbackend.auth.dto.request.LoginRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.LoginResponse;
import com.hospital.hospitalmanagementbackend.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return authenticationService.login(request);
    }

}
