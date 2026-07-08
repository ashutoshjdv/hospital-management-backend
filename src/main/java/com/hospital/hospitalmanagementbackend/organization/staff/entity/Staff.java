package com.hospital.hospitalmanagementbackend.organization.staff.entity;

import com.hospital.hospitalmanagementbackend.auth.entity.User;
import com.hospital.hospitalmanagementbackend.common.entity.AuditableEntity;
import com.hospital.hospitalmanagementbackend.common.enums.EmploymentType;
import com.hospital.hospitalmanagementbackend.common.enums.StaffStatus;
import com.hospital.hospitalmanagementbackend.organization.department.entity.Department;
import com.hospital.hospitalmanagementbackend.organization.designation.entity.Designation;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity.DoctorProfile;
import com.hospital.hospitalmanagementbackend.organization.hospital.entity.Hospital;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "staff", schema = "organization")
@Getter
@Setter
@NoArgsConstructor
public class Staff extends AuditableEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id", nullable = false)
    private Designation designation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporting_manager_id")
    private Staff reportingManager;

    @Column(name = "employee_code", nullable = false, length = 30)
    private String employeeCode;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type", nullable = false)
    private EmploymentType employmentType;

    @Column(precision = 12, scale = 2)
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StaffStatus status;

    @OneToMany(mappedBy = "reportingManager", fetch = FetchType.LAZY)
    private Set<Staff> subordinates = new HashSet<>();

    @OneToOne(mappedBy = "staff", fetch = FetchType.LAZY)
    private DoctorProfile doctorProfile;
}
