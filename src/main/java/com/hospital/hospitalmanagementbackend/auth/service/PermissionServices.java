package com.hospital.hospitalmanagementbackend.auth.service;

import com.hospital.hospitalmanagementbackend.auth.dto.response.PermissionResponse;
import com.hospital.hospitalmanagementbackend.auth.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServices {

    private final PermissionRepository permissionRepository;




    public List<PermissionResponse> getPermissions() {

        return permissionRepository.findAll().stream().map(permission -> new PermissionResponse(permission.getId(),permission.getName(),permission.getDescription())).toList();
    }
}
