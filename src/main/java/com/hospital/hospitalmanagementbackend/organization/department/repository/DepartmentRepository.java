package com.hospital.hospitalmanagementbackend.organization.department.repository;

import com.hospital.hospitalmanagementbackend.organization.department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID>,
        JpaSpecificationExecutor<Department> {

    boolean existsByHospital_IdAndName(
            UUID hospitalId,
            String name
    );

    Page<Department> findByHospital_Id(
            UUID hospitalId,
            Pageable pageable
    );

    boolean existsByHospital_IdAndNameIgnoreCase(
            UUID hospitalId,
            String name
    );

    boolean existsByHospital_IdAndNameIgnoreCaseAndIdNot(
            UUID hospitalId,
            String name,
            UUID id
    );
}
