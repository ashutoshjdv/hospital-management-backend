package com.hospital.hospitalmanagementbackend.organization.department.entity;

import com.hospital.hospitalmanagementbackend.common.entity.AuditableEntity;
import com.hospital.hospitalmanagementbackend.common.enums.Status;
import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments", schema = "organization")
@Getter
@Setter
@NoArgsConstructor
public class Department extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_staff_id")
    private Staff headStaff;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<Staff> staffMembers = new HashSet<>();
}
