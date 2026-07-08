package com.hospital.hospitalmanagementbackend.auth.service;

import com.hospital.hospitalmanagementbackend.auth.dto.request.RoleRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.PermissionResponse;
import com.hospital.hospitalmanagementbackend.auth.dto.response.RoleResponse;
import com.hospital.hospitalmanagementbackend.auth.entity.Permission;
import com.hospital.hospitalmanagementbackend.auth.entity.Role;
import com.hospital.hospitalmanagementbackend.auth.entity.User;
import com.hospital.hospitalmanagementbackend.auth.repository.PermissionRepository;
import com.hospital.hospitalmanagementbackend.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServices {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepository;

    private RoleResponse mapToResponse(Role role) {

        List<PermissionResponse> permissions = role.getPermissions().stream()
                                                    .map(permission ->
                                                            new PermissionResponse(
                                                                    permission.getId(),
                                                                    permission.getName(),
                                                                    permission.getDescription()
                                                            )
                                                    )
                                                    .collect(Collectors.toList());

        return new RoleResponse(
                role.getId(),
                role.getName(),
                role.getDescription(),
                permissions
        );

    }



    public List<RoleResponse> getRolesService() {

        return roleRepo.findAll().stream().map(this::mapToResponse).toList();
    }

    public RoleResponse getRoleByIdService(UUID id) {

        Role role = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role not found") );

        return mapToResponse(role);
    }

    @Transactional
    public String postRoleService(RoleRequest request) {
       Role role = new Role();
       role.setName(request.getName());
       role.setDescription(request.getDescription());

       Set<Permission> permissions = request.getPermissionIds().stream().map(permissionId -> permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found"))).collect(Collectors.toSet());

       role.setPermissions(permissions);

       roleRepo.save(role);

       return "Add role successful";
    }

    @Transactional
    public String updateRoleService(UUID id, RoleRequest request) {
       Role role = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
       
       role.setName(request.getName());
       
       role.setDescription(request.getDescription());

       Set<Permission> permissions = request.getPermissionIds().stream().map(permissionId -> permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found"))).collect(Collectors.toSet());

       role.setPermissions(permissions);

       roleRepo.save(role);

       return "Update role successfull";

    }


    @Transactional
    public String deleteRoleService(UUID id) {
        Role role = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

        for(User user : role.getUsers()) {
            user.getRoles().remove(role);
        }
        roleRepo.delete(role);

        return "delete role successfull";
    }
}
