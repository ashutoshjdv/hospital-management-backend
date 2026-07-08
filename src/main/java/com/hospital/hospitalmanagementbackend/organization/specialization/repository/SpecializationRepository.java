package com.hospital.hospitalmanagementbackend.organization.specialization.repository;

import com.hospital.hospitalmanagementbackend.organization.specialization.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, UUID>, JpaSpecificationExecutor<Specialization> {

    Optional<Specialization> findByName(String name);

    boolean existsByName(String name);
}
