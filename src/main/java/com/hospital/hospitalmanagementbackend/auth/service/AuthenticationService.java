package com.hospital.hospitalmanagementbackend.auth.service;

import com.hospital.hospitalmanagementbackend.auth.dto.request.LoginRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.LoginResponse;
import com.hospital.hospitalmanagementbackend.auth.entity.Users;
import com.hospital.hospitalmanagementbackend.auth.jwt.JWTService;
import com.hospital.hospitalmanagementbackend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public LoginResponse login(LoginRequest request) {

        Users user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash());

        if (!matches) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}
