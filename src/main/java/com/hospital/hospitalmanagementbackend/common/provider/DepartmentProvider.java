package com.hospital.hospitalmanagementbackend.common.provider;

import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.department.entity.Department;
import com.hospital.hospitalmanagementbackend.organization.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DepartmentProvider {

    private final DepartmentRepository departmentRepository;

    public Department getById(UUID id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department",
                                "id",
                                id
                        ));
    }
}