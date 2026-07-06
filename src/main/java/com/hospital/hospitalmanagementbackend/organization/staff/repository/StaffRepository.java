package com.hospital.hospitalmanagementbackend.organization.staff.repository;

import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID>, JpaSpecificationExecutor<Staff> {

    Optional<Staff> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    boolean existsByHospitalIdAndEmployeeCode(
            UUID hospitalId,
            String employeeCode
    );

    Page<Staff> findByDepartmentId(UUID departmentId, Pageable pageable);
}
