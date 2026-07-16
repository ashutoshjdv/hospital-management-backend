package com.hospital.hospitalmanagementbackend.organization.specialization.controller;

import com.hospital.hospitalmanagementbackend.common.constants.ApiMessages;
import com.hospital.hospitalmanagementbackend.common.response.ApiResponse;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.request.CreateSpecializationRequest;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.request.UpdateSpecializationRequest;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.request.UpdateSpecializationStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.response.SpecializationDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.specialization.dto.response.SpecializationListResponse;
import com.hospital.hospitalmanagementbackend.organization.specialization.service.SpecializationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/specializations")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping
    public ResponseEntity<ApiResponse<SpecializationDetailsResponse>> createSpecialization(
            @Valid @RequestBody CreateSpecializationRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.SPECIALIZATION_CREATED,
                        specializationService.createSpecialization(request)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SpecializationDetailsResponse>> getSpecialization(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.SPECIALIZATION_FETCHED,
                        specializationService.getSpecialization(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SpecializationListResponse>>> getSpecializations(
            @PageableDefault(size = 10, sort = "createdAt")
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.SPECIALIZATIONS_FETCHED,
                        specializationService.getSpecializations(pageable)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SpecializationDetailsResponse>> updateSpecialization(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateSpecializationRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.SPECIALIZATION_UPDATED,
                        specializationService.updateSpecialization(id, request)
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<SpecializationDetailsResponse>> updateSpecializationStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateSpecializationStatusRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.SPECIALIZATION_STATUS_UPDATED,
                        specializationService.updateSpecializationStatus(id, request)
                )
        );
    }
}
