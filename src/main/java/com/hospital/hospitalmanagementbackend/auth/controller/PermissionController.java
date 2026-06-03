package com.hospital.hospitalmanagementbackend.auth.controller;

import com.hospital.hospitalmanagementbackend.auth.dto.response.PermissionResponse;
import com.hospital.hospitalmanagementbackend.auth.service.PermissionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionServices permissionServices;

    @GetMapping
    public List<PermissionResponse> getPermissions() {
        return permissionServices.getPermissions();
    }


}
