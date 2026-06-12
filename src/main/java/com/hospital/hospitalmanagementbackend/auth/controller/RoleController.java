package com.hospital.hospitalmanagementbackend.auth.controller;
import org.springframework.security.access.prepost.PreAuthorize;

import com.hospital.hospitalmanagementbackend.auth.dto.request.RoleRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.RoleResponse;
import com.hospital.hospitalmanagementbackend.auth.service.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {


    private final RoleServices roleServices;

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public List<RoleResponse> getRoles() {
        return roleServices.getRolesService();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public RoleResponse getRoleById(@PathVariable UUID id) {
        return roleServices.getRoleByIdService(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public String postRole(@RequestBody RoleRequest request) {
        return roleServices.postRoleService(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public String updateRole(@PathVariable UUID id, @RequestBody RoleRequest request) {
        return roleServices.updateRoleService(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public String deleteRole(@PathVariable UUID id) {
        return roleServices.deleteRoleService(id);
    }
}
