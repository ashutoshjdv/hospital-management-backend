package com.hospital.hospitalmanagementbackend.organization.staff.controller;

import com.hospital.hospitalmanagementbackend.common.constants.ApiMessages;
import com.hospital.hospitalmanagementbackend.common.response.ApiResponse;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.request.CreateStaffRequest;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.request.UpdateStaffRequest;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.request.UpdateStaffStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.response.StaffDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.staff.dto.response.StaffListResponse;
import com.hospital.hospitalmanagementbackend.organization.staff.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<ApiResponse<StaffDetailsResponse>> createStaff(
            @Valid @RequestBody CreateStaffRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.STAFF_CREATED,
                        staffService.createStaff(request)
                )
        );
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<ApiResponse<StaffDetailsResponse>> getStaff(
            @PathVariable UUID staffId) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.STAFF_FETCHED,
                        staffService.getStaff(staffId)
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StaffListResponse>>> getAllStaff(
            @PageableDefault(size = 10, sort = "createdAt")
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.STAFF_LIST_FETCHED,
                        staffService.getAllStaff(pageable)
                )
        );
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<ApiResponse<Page<StaffListResponse>>> getStaffByDepartment(
            @PathVariable UUID departmentId,
            @PageableDefault(size = 10, sort = "createdAt")
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.STAFF_LIST_FETCHED,
                        staffService.getStaffByDepartment(
                                departmentId,
                                pageable
                        )
                )
        );
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<ApiResponse<StaffDetailsResponse>> updateStaff(
            @PathVariable UUID staffId,
            @Valid @RequestBody UpdateStaffRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.STAFF_UPDATED,
                        staffService.updateStaff(
                                staffId,
                                request
                        )
                )
        );
    }

    @PatchMapping("/{staffId}/status")
    public ResponseEntity<ApiResponse<StaffDetailsResponse>> updateStaffStatus(
            @PathVariable UUID staffId,
            @Valid @RequestBody UpdateStaffStatusRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.STAFF_STATUS_UPDATED,
                        staffService.updateStaffStatus(
                                staffId,
                                request
                        )
                )
        );
    }


}
