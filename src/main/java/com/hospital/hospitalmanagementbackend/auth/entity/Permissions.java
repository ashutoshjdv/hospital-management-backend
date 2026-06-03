package com.hospital.hospitalmanagementbackend.auth.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "permissions", schema = "auth")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
