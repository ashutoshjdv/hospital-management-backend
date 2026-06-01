package com.hospital.hospitalmanagementbackend.auth.controller;

import com.hospital.hospitalmanagementbackend.auth.entity.Users;
import com.hospital.hospitalmanagementbackend.auth.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/")
    public List<Users> getUsers() {
        return userServices.getUsersService();


    }
}
