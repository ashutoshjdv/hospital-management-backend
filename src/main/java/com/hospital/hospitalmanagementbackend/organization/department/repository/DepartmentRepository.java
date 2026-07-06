package com.hospital.hospitalmanagementbackend.organization.department.repository;

import com.hospital.hospitalmanagementbackend.organization.department.entity.Departments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, UUID>, JpaSpecificationExecutor<Departments> {

    boolean existsByHospitalIdAndName(UUID hospitalId, String name);

    Page<Departments> findByHospitalId(UUID hospitalId, Pageable pageable);
}
