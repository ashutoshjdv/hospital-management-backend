package com.hospital.hospitalmanagementbackend.organization.designation.repository;

import com.hospital.hospitalmanagementbackend.organization.designation.entity.Designations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DesignationRepository extends JpaRepository<Designations, UUID> {

    Optional<Designations> findByName(String name);

    boolean existsByName(String name);
}
