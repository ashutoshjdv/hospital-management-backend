package com.hospital.hospitalmanagementbackend.common.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private Map<String, ?> errors;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                true,
                message,
                data,
                null,
                LocalDateTime.now()
        );
    }

    public static <T> ApiResponse<T> failure(String message, Map<String, ?> errors) {
        return new ApiResponse<>(
                false,
                message,
                null,
                errors,
                LocalDateTime.now()
        );
    }
}