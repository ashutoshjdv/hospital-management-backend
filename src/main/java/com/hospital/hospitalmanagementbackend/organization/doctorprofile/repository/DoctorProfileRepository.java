package com.hospital.hospitalmanagementbackend.organization.doctorprofile.repository;

import com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity.DoctorProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfiles, UUID> {

    Optional<DoctorProfiles> findByLicenseNumber(String licenseNumber);

    boolean existsByLicenseNumber(String licenseNumber);
}
