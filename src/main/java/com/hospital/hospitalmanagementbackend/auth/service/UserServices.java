package com.hospital.hospitalmanagementbackend.auth.service;

import com.hospital.hospitalmanagementbackend.auth.entity.Users;
import com.hospital.hospitalmanagementbackend.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    UserRepository userRepo;

    @Autowired
    public UserServices(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<Users> getUsersService(){
        return userRepo.findAll();
    }
}
