package com.hospital.hospitalmanagementbackend.auth.repository;

import com.hospital.hospitalmanagementbackend.auth.entity.RefreshTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, UUID> {

    Optional<RefreshTokens> findByToken(String token);
}
