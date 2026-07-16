package com.hospital.hospitalmanagementbackend.organization.doctorprofile.controller;

import com.hospital.hospitalmanagementbackend.common.constants.ApiMessages;
import com.hospital.hospitalmanagementbackend.common.response.ApiResponse;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.dto.request.UpdateDoctorProfileRequest;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.dto.response.DoctorProfileResponse;
import com.hospital.hospitalmanagementbackend.organization.doctorprofile.service.DoctorProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctor-profiles")
@RequiredArgsConstructor
public class DoctorProfileController {

    private final DoctorProfileService doctorProfileService;

    @GetMapping("/{staffId}")
    public ResponseEntity<ApiResponse<DoctorProfileResponse>> getDoctorProfile(
            @PathVariable UUID staffId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DOCTOR_PROFILE_FETCHED,
                        doctorProfileService.getDoctorProfile(staffId)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DoctorProfileResponse>>> getAllDoctorProfiles(
            @PageableDefault(size = 10, sort = "createdAt")
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DOCTOR_PROFILE_LIST_FETCHED,
                        doctorProfileService.getAllDoctorProfiles(pageable)
                )
        );
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<ApiResponse<DoctorProfileResponse>> updateDoctorProfile(
            @PathVariable UUID staffId,
            @Valid @RequestBody UpdateDoctorProfileRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DOCTOR_PROFILE_UPDATED,
                        doctorProfileService.updateDoctorProfile(
                                staffId,
                                request
                        )
                )
        );
    }
}