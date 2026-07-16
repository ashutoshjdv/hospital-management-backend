package com.hospital.hospitalmanagementbackend.organization.hospital.repository;

import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {

    Optional<Hospital> findByHospitalCode(String hospitalCode);

    boolean existsByHospitalCode(String hospitalCode);

    Optional<Hospital> findFirstByOrderByCreatedAtAsc();

}
