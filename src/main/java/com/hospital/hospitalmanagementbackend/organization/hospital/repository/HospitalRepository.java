package com.hospital.hospitalmanagementbackend.organization.hospital.repository;

import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospitals, UUID> {

    Optional<Hospitals> findByHospitalCode(String hospitalCode);

    boolean existsByHospitalCode(String hospitalCode);
}
