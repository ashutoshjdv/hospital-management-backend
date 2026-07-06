package com.hospital.hospitalmanagementbackend.auth.entity;

import com.hospital.hospitalmanagementbackend.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "permissions", schema = "auth")
public class Permissions extends BaseEntity {



    private String name;

    private String description;

}
