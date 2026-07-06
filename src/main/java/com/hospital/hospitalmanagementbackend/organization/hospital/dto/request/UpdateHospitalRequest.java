package com.hospital.hospitalmanagementbackend.organization.hospital.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateHospitalRequest {

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 50)
    private String registrationNumber;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 20)
    private String phone;

    @Size(max = 255)
    private String website;

    private String logoUrl;

    private String address;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 100)
    private String country;

    @Size(max = 20)
    private String postalCode;

    @NotBlank
    private String timezone;

    @NotBlank
    private String currency;
}
