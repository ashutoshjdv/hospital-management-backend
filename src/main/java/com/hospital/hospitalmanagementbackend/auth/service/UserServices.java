package com.hospital.hospitalmanagementbackend.auth.service;

import com.hospital.hospitalmanagementbackend.auth.entity.Users;
import com.hospital.hospitalmanagementbackend.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

}
