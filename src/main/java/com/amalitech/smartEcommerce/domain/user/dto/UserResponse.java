package com.amalitech.smartEcommerce.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String role;
    private String status;

}
