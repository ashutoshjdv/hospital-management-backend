package com.hospital.hospitalmanagementbackend.common.provider;

import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import com.hospital.hospitalmanagementbackend.organization.staff.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StaffProvider {

    private final StaffRepository staffRepository;

    public Staff getById(UUID id) {

        return staffRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Staff",
                                "id",
                                id
                        ));
    }
}
