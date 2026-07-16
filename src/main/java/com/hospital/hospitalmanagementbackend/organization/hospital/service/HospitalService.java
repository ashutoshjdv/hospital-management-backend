package com.hospital.hospitalmanagementbackend.organization.hospital.service;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.hospital.dto.request.UpdateHospitalRequest;
import com.hospital.hospitalmanagementbackend.organization.hospital.dto.response.HospitalResponse;
import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import com.hospital.hospitalmanagementbackend.organization.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Transactional(readOnly = true)
    public HospitalResponse getHospital() {

        log.info("Fetching hospital details.");

        Hospital hospital = getHospitalEntity();

        return mapToResponse(hospital);
    }

    @Transactional
    public HospitalResponse updateHospital(UpdateHospitalRequest request) {

        log.info("Updating hospital details.");

        Hospital hospital = getHospitalEntity();

        hospital.setName(request.getName());
        hospital.setRegistrationNumber(request.getRegistrationNumber());
        hospital.setEmail(request.getEmail());
        hospital.setPhone(request.getPhone());
        hospital.setWebsite(request.getWebsite());
        hospital.setLogoUrl(request.getLogoUrl());
        hospital.setAddress(request.getAddress());
        hospital.setCity(request.getCity());
        hospital.setState(request.getState());
        hospital.setCountry(request.getCountry());
        hospital.setPostalCode(request.getPostalCode());
        hospital.setTimezone(request.getTimezone());
        hospital.setCurrency(request.getCurrency());

        Hospital savedHospital = hospitalRepository.save(hospital);

        log.info("Hospital details updated successfully.");

        return mapToResponse(savedHospital);
    }

    private Hospital getHospitalEntity() {

        return hospitalRepository.findFirstByOrderByCreatedAtAsc()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hospital",
                                "record",
                                "default"
                        ));
    }

    private HospitalResponse mapToResponse(Hospital hospital) {

        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .hospitalCode(hospital.getHospitalCode())
                .registrationNumber(hospital.getRegistrationNumber())
                .email(hospital.getEmail())
                .phone(hospital.getPhone())
                .website(hospital.getWebsite())
                .logoUrl(hospital.getLogoUrl())
                .address(hospital.getAddress())
                .city(hospital.getCity())
                .state(hospital.getState())
                .country(hospital.getCountry())
                .postalCode(hospital.getPostalCode())
                .timezone(hospital.getTimezone())
                .currency(hospital.getCurrency())
                .status(hospital.getStatus())
                .build();
    }

}
