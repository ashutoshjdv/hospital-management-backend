package com.hospital.hospitalmanagementbackend.auth.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles", schema = "auth")
@Data
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)

    @JoinTable(
            name = "role_permissions",
            schema = "auth",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns =
            @JoinColumn(name = "permission_id")
    )
    private Set<Permissions> permissions;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users;

}
