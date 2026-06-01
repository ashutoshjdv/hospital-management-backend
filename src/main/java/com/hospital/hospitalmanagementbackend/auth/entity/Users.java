package com.hospital.hospitalmanagementbackend.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@Table(name = "users", schema = "auth")
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    private String status;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
