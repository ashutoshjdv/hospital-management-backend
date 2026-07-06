package com.hospital.hospitalmanagementbackend.organization.hospital.dto.response;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class HospitalResponse {

    private UUID id;

    private String name;

    private String hospitalCode;

    private String registrationNumber;

    private String email;

    private String phone;

    private String website;

    private String logoUrl;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String timezone;

    private String currency;

    private Status status;
}
