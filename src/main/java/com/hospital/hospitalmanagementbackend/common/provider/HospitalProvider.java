package com.hospital.hospitalmanagementbackend.common.provider;

import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import com.hospital.hospitalmanagementbackend.organization.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalProvider {

    private final HospitalRepository hospitalRepository;

    public Hospital getCurrentHospital() {

        return hospitalRepository.findFirstByOrderByCreatedAtAsc()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hospital",
                                "default",
                                "record"
                        ));
    }
}