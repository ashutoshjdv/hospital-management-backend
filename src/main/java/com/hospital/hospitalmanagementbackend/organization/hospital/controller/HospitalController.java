package com.hospital.hospitalmanagementbackend.organization.hospital.controller;

import com.hospital.hospitalmanagementbackend.common.constants.ApiMessages;
import com.hospital.hospitalmanagementbackend.common.response.ApiResponse;
import com.hospital.hospitalmanagementbackend.organization.hospital.dto.request.UpdateHospitalRequest;
import com.hospital.hospitalmanagementbackend.organization.hospital.dto.response.HospitalResponse;
import com.hospital.hospitalmanagementbackend.organization.hospital.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<ApiResponse<HospitalResponse>> getHospital() {

        HospitalResponse response = hospitalService.getHospital();

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.HOSPITAL_FETCHED,
                        response
                )
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<HospitalResponse>> updateHospital(
            @Valid @RequestBody UpdateHospitalRequest request) {

        HospitalResponse response = hospitalService.updateHospital(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        ApiMessages.HOSPITAL_UPDATED,
                        response
                )
        );
    }

}
