package com.hospital.hospitalmanagementbackend.auth.service;

import com.hospital.hospitalmanagementbackend.auth.dto.request.AssignRoleRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.UserResponse;
import com.hospital.hospitalmanagementbackend.auth.dto.response.UserRoleResponse;
import com.hospital.hospitalmanagementbackend.auth.entity.Roles;
import com.hospital.hospitalmanagementbackend.auth.entity.Users;
import com.hospital.hospitalmanagementbackend.auth.repository.RoleRepository;
import com.hospital.hospitalmanagementbackend.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public List<UserResponse> getUsersService(){
        return userRepo.findAll().stream().map(user -> new UserResponse(user.getId(),user.getEmail(), user.getStatus(), user.isEmailVerified(),
                user.getRoles().stream().map( role -> new UserRoleResponse(role.getId(),role.getName())).toList()
        )).toList();
    }

    public String deactivateUserService(UUID id) {
       Users user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Could not find user"));
       user.setStatus("DEACTIVATED");
       user.setUpdatedAt(LocalDateTime.now());
       userRepo.save(user);
       return "User deactivated successfully";
    }

    public String activateUserService(UUID id) {
        Users user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Could not find user"));
        user.setStatus("ACTIVATED");
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);
        return "User activated successfully";
    }

    @Transactional
    public String verifyUserService(UUID id) {
        Users user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Could not find user"));
        user.setStatus("ACTIVATED");
        user.setEmailVerified(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);
        return "User verified successfully";
    }

    @Transactional
    public String addRoleToUserService(UUID id, AssignRoleRequest request) {
        Users user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Could not find user"));

        Set<Roles> roles = request.getRoleIds().stream().map( roleId -> roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found")) ).collect(Collectors.toSet());

        user.setRoles(roles);

        userRepo.save(user);

        return "Roles updated successfully";
    }
}
