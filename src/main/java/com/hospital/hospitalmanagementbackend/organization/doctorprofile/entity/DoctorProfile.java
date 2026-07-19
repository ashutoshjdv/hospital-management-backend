package com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity;

import com.hospital.hospitalmanagementbackend.organization.specialization.entity.Specialization;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "doctor_profiles", schema = "organization")
@Getter
@Setter
@NoArgsConstructor
public class DoctorProfile {

    @Id
    @Column(name = "staff_id", nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
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

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctor_specializations",
            schema = "organization",
            joinColumns = @JoinColumn(name = "doctor_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specialization> specializations = new HashSet<>();
}