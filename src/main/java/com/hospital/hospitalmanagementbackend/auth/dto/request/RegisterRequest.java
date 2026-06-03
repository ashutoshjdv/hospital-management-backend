package com.hospital.hospitalmanagementbackend.auth.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    @Email
    @NotBlank
    private String email;
    @Size(min = 8)
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain 10 digits"
    )
    private String phone;
    @NotBlank
    private String gender;
    @NotBlank
    private String address;
    @NotBlank
    @Past
    private LocalDate dateOfBirth;
}



