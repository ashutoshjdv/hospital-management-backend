package com.hospital.hospitalmanagementbackend.organization.doctorprofile.repository;

import com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, UUID> {

    boolean existsByLicenseNumberIgnoreCase(String licenseNumber);

    boolean existsByStaff_Id(UUID staffId);

    Optional<DoctorProfile> findByStaff_Id(UUID staffId);
}