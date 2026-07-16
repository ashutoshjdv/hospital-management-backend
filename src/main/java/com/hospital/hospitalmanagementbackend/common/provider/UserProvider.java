package com.hospital.hospitalmanagementbackend.common.provider;

import com.hospital.hospitalmanagementbackend.auth.entity.User;
import com.hospital.hospitalmanagementbackend.auth.repository.UserRepository;
import com.hospital.hospitalmanagementbackend.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProvider {

    private final UserRepository userRepository;

    public User getById(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User",
                                "id",
                                id
                        ));
    }
}
