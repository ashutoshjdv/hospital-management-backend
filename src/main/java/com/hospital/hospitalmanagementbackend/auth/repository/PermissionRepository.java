package com.hospital.hospitalmanagementbackend.auth.repository;

import com.hospital.hospitalmanagementbackend.auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {


}
