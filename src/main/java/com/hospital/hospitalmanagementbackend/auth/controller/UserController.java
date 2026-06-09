package com.hospital.hospitalmanagementbackend.auth.controller;

import com.hospital.hospitalmanagementbackend.auth.dto.request.AssignRoleRequest;
import com.hospital.hospitalmanagementbackend.auth.dto.response.UserResponse;
import com.hospital.hospitalmanagementbackend.auth.dto.response.UserRoleResponse;
import com.hospital.hospitalmanagementbackend.auth.entity.Users;
import com.hospital.hospitalmanagementbackend.auth.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {


   private final UserServices userServices;


    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public List<UserResponse> getUsers() {
        return userServices.getUsersService();
    }

    @PutMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public String deactivateUsers(@PathVariable UUID id) {
        return userServices.deactivateUserService(id);
    }

    @PutMapping("/activate/{id}")
    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    public String activateUsers(@PathVariable UUID id) {
        return userServices.activateUserService(id);
    }
    @PutMapping("/verify/{id}")
    @PreAuthorize("hasAuthority('VERIFY_USER')")
    public String verifyUsers(@PathVariable UUID id) {
        return userServices.verifyUserService(id);
    }
    @PutMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('VERIFY_USER')")
    public String addRoleToUser(@PathVariable UUID id, @RequestBody AssignRoleRequest request) {
        return userServices.addRoleToUserService(id, request);
    }

}


