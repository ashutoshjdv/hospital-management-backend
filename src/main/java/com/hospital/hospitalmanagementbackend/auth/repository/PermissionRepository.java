package com.hospital.hospitalmanagementbackend.auth.repository;

import com.hospital.hospitalmanagementbackend.auth.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permissions, UUID> {


}
