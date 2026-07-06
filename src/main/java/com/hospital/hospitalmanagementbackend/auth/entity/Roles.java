package com.hospital.hospitalmanagementbackend.auth.entity;


import com.hospital.hospitalmanagementbackend.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles", schema = "auth")
@Getter
@Setter
@NoArgsConstructor
public class Roles extends BaseEntity {


    private String name;

    private String description;


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
