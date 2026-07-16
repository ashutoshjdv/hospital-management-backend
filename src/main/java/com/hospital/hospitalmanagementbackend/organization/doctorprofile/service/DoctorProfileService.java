package com.hospital.hospitalmanagementbackend.organization.doctorprofile.service;

import com.hospital.hospitalmanagementbackend.auth.entity.Profile;
import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.dto.request.UpdateDoctorProfileRequest;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.dto.response.DoctorProfileResponse;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.entity.DoctorProfile;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.repository.DoctorProfileRepository;
import com.hospital.hospitalmanagementbackend.organization.specialization.entity.Specialization;
import com.hospital.hospitalmanagementbackend.organization.specialization.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorProfileService {

    private final DoctorProfileRepository doctorProfileRepository;
    private final SpecializationRepository specializationRepository;

    @Transactional(readOnly = true)
    public DoctorProfileResponse getDoctorProfile(UUID staffId) {

        DoctorProfile doctorProfile = doctorProfileRepository.findByStaff_Id(staffId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor Profile",
                                "staffId",
                                staffId
                        ));

        return buildResponse(doctorProfile);
    }

    @Transactional(readOnly = true)
    public Page<DoctorProfileResponse> getAllDoctorProfiles(Pageable pageable) {

        return doctorProfileRepository.findAll(pageable)
                .map(this::buildResponse);
    }

    @Transactional
    public DoctorProfileResponse updateDoctorProfile(
            UUID staffId,
            UpdateDoctorProfileRequest request
    ) {

        DoctorProfile doctorProfile = doctorProfileRepository.findByStaff_Id(staffId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor Profile",
                                "staffId",
                                staffId
                        ));

        Set<Specialization> specializations = request.getSpecializationIds()
                .stream()
                .map(this::getSpecialization)
                .collect(Collectors.toSet());

        doctorProfile.setQualification(request.getQualification());
        doctorProfile.setExperienceYears(request.getExperienceYears());
        doctorProfile.setConsultationFee(request.getConsultationFee());
        doctorProfile.setBio(request.getBio());
        doctorProfile.setSpecializations(specializations);

        DoctorProfile updatedProfile = doctorProfileRepository.save(doctorProfile);

        log.info("Doctor profile updated successfully : {}", updatedProfile.getId());

        return buildResponse(updatedProfile);
    }

    // ==========================================================
    // Private Helper Methods
    // ==========================================================

    private Specialization getSpecialization(UUID specializationId) {

        return specializationRepository.findById(specializationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Specialization",
                                "id",
                                specializationId
                        ));
    }

    private DoctorProfileResponse buildResponse(DoctorProfile doctorProfile) {

        Profile profile = doctorProfile.getStaff()
                .getUser()
                .getProfile();

        String employeeName = "";

        if (profile != null) {

            String firstName = profile.getFirstName() == null ? "" : profile.getFirstName();
            String lastName = profile.getLastName() == null ? "" : profile.getLastName();

            employeeName = (firstName + " " + lastName).trim();
        }

        List<String> specializations = doctorProfile.getSpecializations()
                .stream()
                .map(Specialization::getName)
                .toList();

        return DoctorProfileResponse.builder()
                .id(doctorProfile.getId())
                .staffId(doctorProfile.getStaff().getId())
                .employeeName(employeeName)
                .licenseNumber(doctorProfile.getLicenseNumber())
                .qualification(doctorProfile.getQualification())
                .experienceYears(doctorProfile.getExperienceYears())
                .consultationFee(doctorProfile.getConsultationFee())
                .bio(doctorProfile.getBio())
                .specializations(specializations)
                .build();
    }
}