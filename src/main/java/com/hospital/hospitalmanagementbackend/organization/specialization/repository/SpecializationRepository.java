package com.hospital.hospitalmanagementbackend.organization.specialization.repository;

import com.hospital.hospitalmanagementbackend.organization.specialization.entity.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecializationRepository extends JpaRepository<Specializations, UUID>, JpaSpecificationExecutor<Specializations> {

    Optional<Specializations> findByName(String name);

    boolean existsByName(String name);
}
