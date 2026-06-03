package com.hospital.hospitalmanagementbackend.auth.controller;

import com.hospital.hospitalmanagementbackend.auth.dto.request.RoleRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.RoleResponse;
import com.hospital.hospitalmanagementbackend.auth.service.RoleServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {


    private final RoleServices roleServices;

    @GetMapping
    public List<RoleResponse> getRoles() {
        return roleServices.getRolesService();
    }

    @GetMapping("/{id}")
    public RoleResponse getRoleById(@PathVariable UUID id) {
        return roleServices.getRoleByIdService(id);
    }

    @PostMapping
    public String postRole(@RequestBody RoleRequest request) {
        return roleServices.postRoleService(request);
    }

    @PutMapping("/{id}")
    public String updateRole(@PathVariable UUID id, @RequestBody RoleRequest request) {
        return roleServices.updateRoleService(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable UUID id) {
        return roleServices.deleteRoleService(id);
    }
}
