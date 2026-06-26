package com.hospital.hospitalmanagementbackend.auth.service;

import com.hospital.hospitalmanagementbackend.auth.dto.request.LoginRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.request.LogoutRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.request.RefreshTokenRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.request.RegisterRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.LoginResponse;
import com.hospital.hospitalmanagementbackend.auth.dto.response.RefreshTokenResponse;
import com.hospital.hospitalmanagementbackend.auth.dto.response.RegisterResponse;
import com.hospital.hospitalmanagementbackend.auth.entity.Permissions;
import com.hospital.hospitalmanagementbackend.auth.entity.Profiles;
import com.hospital.hospitalmanagementbackend.auth.entity.RefreshTokens;
import com.hospital.hospitalmanagementbackend.auth.entity.Users;
import com.hospital.hospitalmanagementbackend.auth.jwt.JWTService;
import com.hospital.hospitalmanagementbackend.auth.repository.ProfileRepository;
import com.hospital.hospitalmanagementbackend.auth.repository.RefreshTokensRepository;
import com.hospital.hospitalmanagementbackend.auth.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final RefreshTokensRepository refreshTokensRepository;

    @Transactional
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

        String accessToken =
                jwtService.generateAccessToken(user.getEmail());

        String refreshToken =
                jwtService.generateRefreshToken(user.getEmail());

        RefreshTokens refreshTokenEntity = RefreshTokens.builder()
                                                .token(refreshToken)
                                                .user(user)
                                                .expiresAt(
                                                        LocalDateTime.now().plusDays(7)
                                                )
                                                .build();

        refreshTokensRepository.save(refreshTokenEntity);

        String[] authorities = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(
                        Permissions::getName)
                .distinct()
                .toArray(String[]::new);

//        System.out.println(authorities);

        return new LoginResponse(accessToken, refreshToken, user.getEmail(), authorities);
    }

    @Transactional
    public RegisterResponse register(@Valid RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){

            throw new RuntimeException("User exists try another email");
        }
        Users user = new Users();

        user.setEmail(request.getEmail());

        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        user.setStatus("PENDING");

        user.setCreatedAt(LocalDateTime.now());

        user.setEmailVerified(false);

        Users savedUser = userRepository.save(user);

        Profiles profile = new Profiles();

        profile.setFirstName(request.getFirstName());

        profile.setLastName(request.getLastName());

        profile.setAddress(request.getAddress());

        profile.setGender(request.getGender());

        profile.setPhone(request.getPhone());

        profile.setDateOfBirth(request.getDateOfBirth());

        profile.setCreatedAt(LocalDateTime.now());

        profile.setUser(savedUser);

        profileRepository.save(profile);

        return new RegisterResponse(savedUser.getId(),savedUser.getEmail(), "user is registered successfully");
    }

    @Transactional
    public RefreshTokenResponse refresh(
            RefreshTokenRequest request
    ) {

        RefreshTokens refreshToken =
                refreshTokensRepository
                        .findByToken(
                                request.getRefreshToken()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Refresh token not found"
                                )
                        );

        if (refreshToken.isRevoked()) {
            throw new RuntimeException(
                    "Refresh token revoked"
            );
        }

        if (refreshToken.getExpiresAt()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException(
                    "Refresh token expired"
            );
        }

        String email =
                jwtService.extractUsername(
                        refreshToken.getToken()
                );

        String accessToken =
                jwtService.generateAccessToken(
                        email
                );

        return new RefreshTokenResponse(
                accessToken
        );
    }

    @Transactional
    public void logout(
            LogoutRequest request
    ) {

        RefreshTokens refreshToken =
                refreshTokensRepository
                        .findByToken(
                                request.getRefreshToken()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Refresh token not found"
                                )
                        );

        refreshToken.setRevoked(true);

        refreshTokensRepository.save(
                refreshToken
        );
    }
}
