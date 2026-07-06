package com.hospital.hospitalmanagementbackend.auth.entity;

import com.hospital.hospitalmanagementbackend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users", schema = "auth")
public class Users extends BaseEntity {



    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    private String status;

    @Column(name = "is_email_verified")
    private boolean isEmailVerified;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            schema = "auth",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Profiles profile;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RefreshTokens> refreshTokens = new ArrayList<>();


}
