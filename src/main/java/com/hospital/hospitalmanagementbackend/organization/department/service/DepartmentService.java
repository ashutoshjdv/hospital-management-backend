package com.hospital.hospitalmanagementbackend.organization.department.service;

import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.common.exception.DuplicateResourceException;
import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.common.provider.HospitalProvider;
import com.hospital.hospitalmanagementbackend.organization.department.dto.request.CreateDepartmentRequest;
import com.hospital.hospitalmanagementbackend.organization.department.dto.request.UpdateDepartmentRequest;
import com.hospital.hospitalmanagementbackend.organization.department.dto.request.UpdateDepartmentStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.department.dto.response.DepartmentDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.department.dto.response.DepartmentListResponse;
import com.hospital.hospitalmanagementbackend.organization.department.entity.Department;
import com.hospital.hospitalmanagementbackend.organization.department.repository.DepartmentRepository;
import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalProvider hospitalProvider;

    // =====================================================
    // Create Department
    // =====================================================

    @Transactional
    public DepartmentDetailsResponse createDepartment(CreateDepartmentRequest request) {

        Hospital hospital = hospitalProvider.getCurrentHospital();

        validateDuplicateDepartmentName(hospital.getId(), request.getName());

        Department department = new Department();

        department.setHospital(hospital);
        department.setName(request.getName().trim());
        department.setDescription(request.getDescription());
        department.setStatus(Status.ACTIVE);

        Department savedDepartment = departmentRepository.save(department);

        log.info("Department created : {}", savedDepartment.getId());

        return mapToDetailsResponse(savedDepartment);
    }

    // =====================================================
    // Get By Id
    // =====================================================

    @Transactional(readOnly = true)
    public DepartmentDetailsResponse getDepartment(UUID id) {

        return mapToDetailsResponse(getDepartmentEntity(id));
    }

    // =====================================================
    // Get All
    // =====================================================

    @Transactional(readOnly = true)
    public Page<DepartmentListResponse> getDepartments(Pageable pageable) {

        Hospital hospital = hospitalProvider.getCurrentHospital();

        return departmentRepository
                .findByHospital_Id(hospital.getId(), pageable)
                .map(this::mapToListResponse);
    }

    // =====================================================
    // Update
    // =====================================================

    @Transactional
    public DepartmentDetailsResponse updateDepartment(
            UUID id,
            UpdateDepartmentRequest request
    ) {

        Department department = getDepartmentEntity(id);

        validateDuplicateDepartmentNameForUpdate(
                department.getHospital().getId(),
                request.getName(),
                id
        );

        department.setName(request.getName().trim());
        department.setDescription(request.getDescription());

        Department updatedDepartment = departmentRepository.save(department);

        log.info("Department updated : {}", id);

        return mapToDetailsResponse(updatedDepartment);
    }

    // =====================================================
    // Update Status
    // =====================================================

    @Transactional
    public DepartmentDetailsResponse updateDepartmentStatus(
            UUID id,
            UpdateDepartmentStatusRequest request
    ) {

        Department department = getDepartmentEntity(id);

        department.setStatus(request.getStatus());

        Department updatedDepartment = departmentRepository.save(department);

        return mapToDetailsResponse(updatedDepartment);
    }

    // =====================================================
    // Private Methods
    // =====================================================

    private Department getDepartmentEntity(UUID id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department",
                                "id",
                                id
                        ));
    }

    private void validateDuplicateDepartmentName(
            UUID hospitalId,
            String name
    ) {

        if (departmentRepository.existsByHospital_IdAndNameIgnoreCase(
                hospitalId,
                name.trim()
        )) {

            throw new DuplicateResourceException(
                    "Department",
                    "name",
                    name
            );
        }
    }

    private void validateDuplicateDepartmentNameForUpdate(
            UUID hospitalId,
            String name,
            UUID departmentId
    ) {

        if (departmentRepository.existsByHospital_IdAndNameIgnoreCaseAndIdNot(
                hospitalId,
                name.trim(),
                departmentId
        )) {

            throw new DuplicateResourceException(
                    "Department",
                    "name",
                    name
            );
        }
    }

    // =====================================================
    // Mapping
    // =====================================================

    private DepartmentDetailsResponse mapToDetailsResponse(
            Department department
    ) {

        return DepartmentDetailsResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .headStaffId(
                        department.getHeadStaff() != null
                                ? department.getHeadStaff().getId()
                                : null
                )
                .headStaffName(
                        department.getHeadStaff() != null
                                ? department.getHeadStaff().getUser().getProfile().getFirstName()+ " "
                                  + department.getHeadStaff().getUser().getProfile().getLastName()
                                : null
                )
                .status(department.getStatus())
                .createdAt(department.getCreatedAt())
                .build();
    }

    private DepartmentListResponse mapToListResponse(
            Department department
    ) {

        return DepartmentListResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .headName(
                        department.getHeadStaff() != null
                                ? department.getHeadStaff().getUser().getProfile().getFirstName()+ " "
                                  + department.getHeadStaff().getUser().getProfile().getLastName()
                                : null
                )
                .status(department.getStatus())
                .build();
    }
}
