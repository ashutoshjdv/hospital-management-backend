package com.hospital.hospitalmanagementbackend.auth.entity;

import com.hospital.hospitalmanagementbackend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profiles", schema = "auth")
public class Profiles extends BaseEntity {



    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String phone;

    private String gender;

    private String address;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

}
