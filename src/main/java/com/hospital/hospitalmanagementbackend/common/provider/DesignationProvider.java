package com.hospital.hospitalmanagementbackend.common.provider;

import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.designation.entity.Designation;
import com.hospital.hospitalmanagementbackend.organization.designation.repository.DesignationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DesignationProvider {

    private final DesignationRepository designationRepository;

    public Designation getById(UUID id) {

        return designationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Designation",
                                "id",
                                id
                        ));
    }
}