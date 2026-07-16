package com.hospital.hospitalmanagementbackend.organization.department.controller;

import com.hospital.hospitalmanagementbackend.common.constants.ApiMessages;
import com.hospital.hospitalmanagementbackend.common.response.ApiResponse;
import com.hospital.hospitalmanagementbackend.organization.department.dto.request.CreateDepartmentRequest;
import com.hospital.hospitalmanagementbackend.organization.department.dto.request.UpdateDepartmentRequest;
import com.hospital.hospitalmanagementbackend.organization.department.dto.request.UpdateDepartmentStatusRequest;
import com.hospital.hospitalmanagementbackend.organization.department.dto.response.DepartmentDetailsResponse;
import com.hospital.hospitalmanagementbackend.organization.department.dto.response.DepartmentListResponse;
import com.hospital.hospitalmanagementbackend.organization.department.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentDetailsResponse>> createDepartment(
            @Valid @RequestBody CreateDepartmentRequest request) {

        DepartmentDetailsResponse response =
                departmentService.createDepartment(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DEPARTMENT_CREATED,
                        response
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentDetailsResponse>> getDepartment(
            @PathVariable UUID id) {

        DepartmentDetailsResponse response =
                departmentService.getDepartment(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DEPARTMENT_FETCHED,
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DepartmentListResponse>>> getDepartments(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

        Page<DepartmentListResponse> response =
                departmentService.getDepartments(pageable);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DEPARTMENTS_FETCHED,
                        response
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentDetailsResponse>> updateDepartment(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDepartmentRequest request) {

        DepartmentDetailsResponse response =
                departmentService.updateDepartment(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DEPARTMENT_UPDATED,
                        response
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DepartmentDetailsResponse>> updateDepartmentStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDepartmentStatusRequest request) {

        DepartmentDetailsResponse response =
                departmentService.updateDepartmentStatus(id, request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.DEPARTMENT_STATUS_UPDATED,
                        response
                )
        );
    }
}