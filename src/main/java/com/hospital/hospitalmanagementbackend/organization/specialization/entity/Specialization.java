package com.hospital.hospitalmanagementbackend.organization.specialization.entity;

import com.hospital.hospitalmanagementbackend.common.entity.BaseEntity;
import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity.DoctorProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specializations", schema = "organization")
@Getter
@Setter
@NoArgsConstructor
public class Specialization extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToMany(mappedBy = "specializations")
    private Set<DoctorProfile> doctorProfiles = new HashSet<>();
}
