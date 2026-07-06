package com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity;

import com.hospital.hospitalmanagementbackend.common.entity.AuditableEntity;
import com.hospital.hospitalmanagementbackend.organization.specialization.entity.Specializations;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor_profiles", schema = "organization")
@Getter
@Setter
@NoArgsConstructor
public class DoctorProfiles extends AuditableEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "license_number", nullable = false, length = 100)
    private String licenseNumber;

    @Column(length = 255)
    private String qualification;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "consultation_fee", precision = 10, scale = 2)
    private BigDecimal consultationFee;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctor_specializations",
            schema = "organization",
            joinColumns = @JoinColumn(name = "doctor_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specializations> specializations = new HashSet<>();
}
