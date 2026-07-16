package com.hospital.hospitalmanagementbackend.organization.designation.controller;

import com.hospital.hospitalmanagementbackend.common.constants.ApiMessages;
import com.hospital.hospitalmanagementbackend.common.response.ApiResponse;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.request.CreateDesignationRequest;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.request.UpdateDesignationRequest;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.request.UpdateDesignationStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.response.DesignationDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.designation.dto.response.DesignationListResponse;
import com.hospital.hospitalmanagementbackend.organization.designation.service.DesignationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/designations")
@RequiredArgsConstructor
public class DesignationController {

    private final DesignationService designationService;

    @PostMapping
    public ResponseEntity<ApiResponse<DesignationDetailsResponse>> createDesignation(
            @Valid @RequestBody CreateDesignationRequest request) {

        DesignationDetailsResponse response =
                designationService.createDesignation(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DESIGNATION_CREATED,
                        response
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DesignationDetailsResponse>> getDesignationById(
            @PathVariable UUID id) {

        DesignationDetailsResponse response =
                designationService.getDesignationById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DESIGNATION_FETCHED,
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DesignationListResponse>>> getAllDesignations(
            Pageable pageable) {

        Page<DesignationListResponse> response =
                designationService.getAllDesignations(pageable);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DESIGNATIONS_FETCHED,
                        response
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DesignationDetailsResponse>> updateDesignation(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDesignationRequest request) {

        DesignationDetailsResponse response =
                designationService.updateDesignation(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DESIGNATION_UPDATED,
                        response
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DesignationDetailsResponse>> changeDesignationStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDesignationStatusRequest request) {

        DesignationDetailsResponse response =
                designationService.changeDesignationStatus(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DESIGNATION_STATUS_UPDATED,
                        response
                )
        );
    }

}