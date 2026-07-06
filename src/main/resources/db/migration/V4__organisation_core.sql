-- ============================================================
-- Module      : Organization
-- Migration   : V4
-- Description : Create Organization Schema and Core Tables
-- ============================================================

CREATE SCHEMA IF NOT EXISTS organization;

-- ============================================================
-- Hospitals
-- ============================================================

CREATE TABLE organization.hospitals
(
    id UUID PRIMARY KEY,

    name VARCHAR(150) NOT NULL,

    hospital_code VARCHAR(20) NOT NULL,

    registration_number VARCHAR(50),

    email VARCHAR(100),

    phone VARCHAR(20),

    website VARCHAR(255),

    logo_url TEXT,

    address TEXT,

    city VARCHAR(100),

    state VARCHAR(100),

    country VARCHAR(100),

    postal_code VARCHAR(20),

    timezone VARCHAR(50),

    currency VARCHAR(10),

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT uk_hospital_code
        UNIQUE (hospital_code),

    CONSTRAINT chk_hospital_status
        CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

-- ============================================================
-- Designations
-- ============================================================

CREATE TABLE organization.designations
(
    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    description TEXT,

    level INTEGER NOT NULL,

    category VARCHAR(50) NOT NULL,

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_designation_name
        UNIQUE (name),

    CONSTRAINT chk_designation_level
        CHECK (level > 0),

    CONSTRAINT chk_designation_status
        CHECK (status IN ('ACTIVE', 'INACTIVE')),

    CONSTRAINT chk_designation_category
        CHECK (
            category IN (
                         'DOCTOR',
                         'NURSE',
                         'PHARMACIST',
                         'LAB_TECHNICIAN',
                         'RECEPTIONIST',
                         'ADMINISTRATION',
                         'FINANCE',
                         'SUPPORT'
                )
            )
);

-- ============================================================
-- Departments
-- ============================================================

CREATE TABLE organization.departments
(
    id UUID PRIMARY KEY,

    hospital_id UUID NOT NULL,

    name VARCHAR(100) NOT NULL,

    description TEXT,

    head_staff_id UUID,

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_department_hospital
        FOREIGN KEY (hospital_id)
            REFERENCES organization.hospitals(id)
            ON DELETE RESTRICT,

    CONSTRAINT uk_department_name
        UNIQUE (hospital_id, name),

    CONSTRAINT chk_department_status
        CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

-- ============================================================
-- Staff
-- ============================================================

CREATE TABLE organization.staff
(
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    hospital_id UUID NOT NULL,

    department_id UUID NOT NULL,

    designation_id UUID NOT NULL,

    reporting_manager_id UUID,

    employee_code VARCHAR(30) NOT NULL,

    joining_date DATE NOT NULL,

    employment_type VARCHAR(20) NOT NULL,

    salary NUMERIC(12,2),

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_staff_user
        FOREIGN KEY (user_id)
            REFERENCES auth.users(id)
            ON DELETE RESTRICT,

    CONSTRAINT fk_staff_hospital
        FOREIGN KEY (hospital_id)
            REFERENCES organization.hospitals(id)
            ON DELETE RESTRICT,

    CONSTRAINT fk_staff_department
        FOREIGN KEY (department_id)
            REFERENCES organization.departments(id)
            ON DELETE RESTRICT,

    CONSTRAINT fk_staff_designation
        FOREIGN KEY (designation_id)
            REFERENCES organization.designations(id)
            ON DELETE RESTRICT,

    CONSTRAINT fk_staff_reporting_manager
        FOREIGN KEY (reporting_manager_id)
            REFERENCES organization.staff(id)
            ON DELETE RESTRICT,

    CONSTRAINT uk_staff_user
        UNIQUE (user_id),

    CONSTRAINT uk_staff_employee_code
        UNIQUE (hospital_id, employee_code),

    CONSTRAINT chk_staff_salary
        CHECK (
            salary IS NULL
                OR salary >= 0
            ),

    CONSTRAINT chk_staff_status
        CHECK (
            status IN (
                       'ACTIVE',
                       'INACTIVE',
                       'ON_LEAVE',
                       'SUSPENDED',
                       'RETIRED'
                )
            ),

    CONSTRAINT chk_staff_employment_type
        CHECK (
            employment_type IN (
                                'FULL_TIME',
                                'PART_TIME',
                                'VISITING',
                                'CONTRACT',
                                'INTERN'
                )
            )
);

-- ============================================================
-- Doctor Profiles
-- ============================================================

CREATE TABLE organization.doctor_profiles
(
    staff_id UUID PRIMARY KEY,

    license_number VARCHAR(100) NOT NULL,

    qualification VARCHAR(255),

    experience_years INTEGER DEFAULT 0,

    consultation_fee NUMERIC(10,2),

    bio TEXT,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP,

    CONSTRAINT fk_doctor_profile_staff
        FOREIGN KEY (staff_id)
            REFERENCES organization.staff(id)
            ON DELETE RESTRICT,

    CONSTRAINT uk_doctor_license
        UNIQUE (license_number),

    CONSTRAINT chk_doctor_experience
        CHECK (experience_years >= 0),

    CONSTRAINT chk_doctor_consultation_fee
        CHECK (
            consultation_fee IS NULL
                OR consultation_fee >= 0
            )
);

-- ============================================================
-- Specializations
-- ============================================================

CREATE TABLE organization.specializations
(
    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    description TEXT,

    status VARCHAR(20) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_specialization_name
        UNIQUE (name),

    CONSTRAINT chk_specialization_status
        CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

-- ============================================================
-- Doctor Specializations
-- ============================================================

CREATE TABLE organization.doctor_specializations
(
    doctor_profile_id UUID NOT NULL,

    specialization_id UUID NOT NULL,

    PRIMARY KEY
        (
         doctor_profile_id,
         specialization_id
            ),

    CONSTRAINT fk_ds_doctor
        FOREIGN KEY (doctor_profile_id)
            REFERENCES organization.doctor_profiles(staff_id)
            ON DELETE CASCADE,

    CONSTRAINT fk_ds_specialization
        FOREIGN KEY (specialization_id)
            REFERENCES organization.specializations(id)
            ON DELETE CASCADE
);

-- ============================================================
-- Add delayed foreign key
-- ============================================================

ALTER TABLE organization.departments
    ADD CONSTRAINT fk_department_head_staff
        FOREIGN KEY (head_staff_id)
            REFERENCES organization.staff(id)
            ON DELETE RESTRICT;

-- ============================================================
-- Indexes
-- ============================================================

CREATE INDEX idx_department_hospital
    ON organization.departments(hospital_id);

CREATE INDEX idx_staff_user
    ON organization.staff(user_id);

CREATE INDEX idx_staff_department
    ON organization.staff(department_id);

CREATE INDEX idx_staff_hospital
    ON organization.staff(hospital_id);

CREATE INDEX idx_staff_designation
    ON organization.staff(designation_id);

CREATE INDEX idx_staff_reporting_manager
    ON organization.staff(reporting_manager_id);

CREATE INDEX idx_doctor_license
    ON organization.doctor_profiles(license_number);

CREATE INDEX idx_specialization_name
    ON organization.specializations(name);

CREATE INDEX idx_doctor_specialization_specialization
    ON organization.doctor_specializations(specialization_id);