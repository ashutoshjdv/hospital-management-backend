package com.hospital.hospitalmanagementbackend.organization.designation.entity;

import com.hospital.hospitalmanagementbackend.common.entity.BaseEntity;
import com.hospital.hospitalmanagementbackend.common.enums.DesignationCategory;
import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "designations", schema = "organization")
@Getter
@Setter
@NoArgsConstructor
public class Designation extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer level;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DesignationCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "designation", fetch = FetchType.LAZY)
    private Set<Staff> staffMembers = new HashSet<>();
}
