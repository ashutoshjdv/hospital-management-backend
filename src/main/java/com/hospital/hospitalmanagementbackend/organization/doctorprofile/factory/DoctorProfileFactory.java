package com.hospital.hospitalmanagementbackend.organization.doctorprofile.factory;

import com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity.DoctorProfile;
import com.hospital.hospitalmanagementbackend.organization.staff.entity.Staff;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DoctorProfileFactory {

    public DoctorProfile create(Staff staff) {

        DoctorProfile profile = new DoctorProfile();

        profile.setStaff(staff);

        profile.setLicenseNumber(generateLicenseNumber());

        profile.setQualification("");

        profile.setExperienceYears(0);

        profile.setConsultationFee(BigDecimal.ZERO);

        profile.setBio("");

        return profile;
    }

    private String generateLicenseNumber() {

        return "DOC-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }
}