package com.hospital.hospitalmanagementbackend.organization.staff.repository;

import com.hospital.hospitalmanagementbackend.common.enums.StaffStatus;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staff, UUID>,
        JpaSpecificationExecutor<Staff> {

    boolean existsByUser_Id(UUID userId);

    boolean existsByUser_IdAndIdNot(
            UUID userId,
            UUID staffId
    );

    boolean existsByHospital_IdAndEmployeeCodeIgnoreCase(
            UUID hospitalId,
            String employeeCode
    );

    boolean existsByHospital_IdAndEmployeeCodeIgnoreCaseAndIdNot(
            UUID hospitalId,
            String employeeCode,
            UUID staffId
    );

    Page<Staff> findByDepartment_Id(
            UUID departmentId,
            Pageable pageable
    );

    long countByHospital_Id(UUID hospitalId);

    Page<Staff> findByStatus(
            StaffStatus status,
            Pageable pageable
    );
}